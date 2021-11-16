package com.agh.vacation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Filip Piwosz
 */
class CriteriaJSONLoader {
    private static final String INDEX_MAP_FIELD_NAME = "indexMap";
    private static final String MATRIX_FIELD_NAME = "matrix";

    private CriteriaJSONLoader() {
    }

    static ComparisonMatrix<Criterion> loadCriteria(Path path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(path.toFile());

        JsonNode indexMapJsonNode = node.findValue(INDEX_MAP_FIELD_NAME);
        //there is a warning for unchecked assignment
        //afaik the only possible way to fix this is to use reflection
        Map<Criterion, Integer> map = objectMapper.treeToValue(indexMapJsonNode, HashMap.class);
        IndexMap<Criterion> indexMap = new IndexMap<>(map);

        JsonNode matrixJsonNode = node.findValue(MATRIX_FIELD_NAME);
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
}
