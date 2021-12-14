package com.agh.vacation.fileloading;

import com.agh.vacation.ComparisonMatrix;
import com.agh.vacation.CriteriaPrioritiesMap;
import com.agh.vacation.Criterion;
import com.agh.vacation.IndexMap;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import static com.agh.vacation.EigenvalueCalculator.calculateEigenvalues;

/**
 *
 */
public class CriteriaJSONLoader {
    private static final String INDEX_MAP_FIELD_NAME = "indexMap";
    private static final String MATRIX_FIELD_NAME = "matrix";
    private static final String TOP_CRITERIA_FIELD_NAME = "topCriteria";
    private static final String TOP_COMPARISON_MATRIX_FIELD_NAME = "topComparisonMatrix";
    private static final String SUB_CRITERIA_FIELD_NAME = "subCriteria";
    private static final String COMPARISON_MATRIX_FIELD_NAME = "comparisonMatrix";

    private CriteriaJSONLoader() {
    }

    /**
     * @param path - path to .json file with criteria
     * @return - a wrapper object for Map with entries <Criterion, Priority in Double value>
     * @throws IOException - regular io error or json parsing error
     */
    public static CriteriaPrioritiesMap loadCriteria(Path path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(path.toFile());

        JsonNode topCriteriaNode = node.findValue(TOP_CRITERIA_FIELD_NAME);
        JsonNode topPCMatrix = node.findValue(TOP_COMPARISON_MATRIX_FIELD_NAME);
        Map<Criterion, Double> onlyLeavesMap = new HashMap<>();
        Map<Criterion, Double> allPrioritiesMap = new HashMap<>();

        //start of recursion
        loadCriteria(topCriteriaNode, topPCMatrix, null, allPrioritiesMap, onlyLeavesMap);


        return new CriteriaPrioritiesMap(onlyLeavesMap);
    }

    private static void loadCriteria(JsonNode criteriaListNode, JsonNode comparisonMatrixNode,
                                     Criterion fatherCriterion,
                                     Map<Criterion, Double> allPrioritiesMap,
                                     Map<Criterion, Double> onlyLeavesMap) throws IOException {

        //1. get comparison matrix from current level
        ComparisonMatrix<Criterion> criterionComparisonMatrix =
                obtainComparisonMatrixOfCurrentLevel(comparisonMatrixNode);

        //2. calculate priorities in this level
        Map<Criterion, Double> currentLevelCriteriaPriorities = calculateEigenvalues(criterionComparisonMatrix);

        //3. obtain father priority if exists
        Double fatherPriority = 1.0;
        if (allPrioritiesMap.containsKey(fatherCriterion)) {
            fatherPriority = allPrioritiesMap.get(fatherCriterion);
        }

        //4. iterate over given list of criteria
        Iterator<Entry<String, JsonNode>> criteriaIterator = criteriaListNode.fields();
        while (criteriaIterator.hasNext()) {
            Entry<String, JsonNode> entry = criteriaIterator.next();
            String criterionName = entry.getKey();

            Criterion criterion = new Criterion(criterionName);
            JsonNode criterionNode = entry.getValue();

            //5. put priority in all priorities map
            Double priority = currentLevelCriteriaPriorities.get(criterion) * fatherPriority;
            allPrioritiesMap.put(criterion, priority);

            //if Criterion doesn't have  subCriteria
            if (criterionNode.isEmpty()) {
                //then this is a leaf
                onlyLeavesMap.put(criterion, priority);
            } else {
                JsonNode subCriteriaListNode = findTopValue(criterionNode, SUB_CRITERIA_FIELD_NAME);
                JsonNode subComparisonMatrixNode = findTopValue(criterionNode, COMPARISON_MATRIX_FIELD_NAME);
                //recursive call
                loadCriteria(subCriteriaListNode, subComparisonMatrixNode, criterion, allPrioritiesMap, onlyLeavesMap);
            }
        }

    }

    private static double[][] fromFractionArray(Fraction[][] array) {
        int length = array.length;
        double[][] result = new double[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                result[i][j] = array[i][j].value;
            }
        }
        return result;
    }

    private static Map<Criterion, Integer> obtainIndexMap(JsonNode indexMapJsonNode) {
        Map<Criterion, Integer> map = new HashMap<>();
        Iterator<Entry<String, JsonNode>> iterator = indexMapJsonNode.fields();
        while (iterator.hasNext()) {
            Entry<String, JsonNode> entry = iterator.next();
            Criterion criterion = new Criterion(entry.getKey());
            Integer index = entry.getValue().intValue();
            map.put(criterion, index);
        }
        return map;
    }

    private static JsonNode findTopValue(JsonNode node, String valueName) {
        Iterator<Entry<String, JsonNode>> iterator = node.fields();
        while (iterator.hasNext()) {
            Entry<String, JsonNode> entry = iterator.next();
            if (entry.getKey().equals(valueName)) {
                return entry.getValue();
            }
        }
        throw new IllegalArgumentException();
    }

    private static ComparisonMatrix<Criterion> obtainComparisonMatrixOfCurrentLevel(JsonNode comparisonMatrixNode)
            throws JsonProcessingException {
        JsonNode indexMapJsonNode = findTopValue(comparisonMatrixNode, INDEX_MAP_FIELD_NAME);

        IndexMap<Criterion> indexMap = new IndexMap<>(obtainIndexMap(indexMapJsonNode));

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode matrixJsonNode = findTopValue(comparisonMatrixNode, MATRIX_FIELD_NAME);
        Fraction[][] fractions = objectMapper.treeToValue(matrixJsonNode, Fraction[][].class);
        RealMatrix realMatrix = new Array2DRowRealMatrix(fromFractionArray(fractions));

        return new ComparisonMatrix<>(realMatrix, indexMap);
    }
}
