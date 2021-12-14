package com.agh.vacation.fileloading;

import com.agh.vacation.ComparisonMatrix;
import com.agh.vacation.CriteriaPrioritiesMap;
import com.agh.vacation.Criterion;
import com.agh.vacation.IndexMap;
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

    private CriteriaJSONLoader() {
    }

    public static CriteriaPrioritiesMap loadCriteriaaa(Path path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(path.toFile());

        JsonNode topCriteriaNode = node.findValue("topCriteria");
        JsonNode topPCMatrix = node.findValue("topComparisonMatrix");
        Map<Criterion, Double> onlyLeavesMap = new HashMap<>();
        Map<Criterion, Double> allPrioritiesMap = new HashMap<>();
        loadCriteriaa(topCriteriaNode, topPCMatrix, null, allPrioritiesMap, onlyLeavesMap);


        return new CriteriaPrioritiesMap(onlyLeavesMap);
    }

    public static void loadCriteriaa(JsonNode criteriaListNode, JsonNode comparisonMatrixNode,
                                     Criterion fatherCriterion,
                                     Map<Criterion, Double> allPrioritiesMap,
                                     Map<Criterion, Double> onlyLeavesMap) throws IOException {

        JsonNode indexMapJsonNode = findTopValue(comparisonMatrixNode, INDEX_MAP_FIELD_NAME);

        IndexMap<Criterion> indexMap = new IndexMap<>(obtainIndexMap(indexMapJsonNode));

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode matrixJsonNode = findTopValue(comparisonMatrixNode, MATRIX_FIELD_NAME);
        Fraction[][] fractions = objectMapper.treeToValue(matrixJsonNode, Fraction[][].class);
        RealMatrix realMatrix = new Array2DRowRealMatrix(fromFractionArray(fractions));

        ComparisonMatrix<Criterion> criterionComparisonMatrix = new ComparisonMatrix<>(realMatrix, indexMap);
        Map<Criterion, Double> currentLevelCriteriaPriorities = calculateEigenvalues(criterionComparisonMatrix);
        Iterator<Entry<String, JsonNode>> criteriaIterator = criteriaListNode.fields();
        Double fatherPriority = 1.0;
        if (allPrioritiesMap.containsKey(fatherCriterion)) {
            fatherPriority = allPrioritiesMap.get(fatherCriterion);
        }
        while (criteriaIterator.hasNext()) {
            Entry<String, JsonNode> entry = criteriaIterator.next();
            String criterionName = entry.getKey();
            Criterion criterion = new Criterion(criterionName);
            JsonNode criterionNode = entry.getValue();

            Double priority = currentLevelCriteriaPriorities.get(criterion) * fatherPriority;
            allPrioritiesMap.put(criterion, priority);

            if (criterionNode.isEmpty()) {
                //
                onlyLeavesMap.put(criterion, priority);
            } else {

                JsonNode subCriteriaListNode = findTopValue(criterionNode, "subCriteria");
                JsonNode subComparisonMatrixNode = findTopValue(criterionNode, "comparisonMatrix");
                loadCriteriaa(subCriteriaListNode, subComparisonMatrixNode, criterion, allPrioritiesMap, onlyLeavesMap);
            }
        }

    }

    public static ComparisonMatrix<Criterion> loadCriteria(Path path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(path.toFile());

        JsonNode indexMapJsonNode = findTopValue(node, INDEX_MAP_FIELD_NAME);

        IndexMap<Criterion> indexMap = new IndexMap<>(obtainIndexMap(indexMapJsonNode));

        JsonNode matrixJsonNode = findTopValue(node, MATRIX_FIELD_NAME);
        Fraction[][] fractions = objectMapper.treeToValue(matrixJsonNode, Fraction[][].class);
        RealMatrix realMatrix = new Array2DRowRealMatrix(fromFractionArray(fractions));

        return new ComparisonMatrix<>(realMatrix, indexMap);
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
}
