package com.agh.vacation;

public class Data {
    final static PairwiseMatrix matrixCriterions = new PairwiseMatrix(new double[][]{
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1}
    });
    final static PairwiseMatrix matrixValueForMoney = new PairwiseMatrix(new double[][]{
                    //Venice    Rome    Lisbon     Madrid     Warsaw
            /*Venice*/{1,       1/3,       1/4,      1/4,      1/4},
            /*Rome*/  {3/1,      1,        3/4,      3/4,      3/4},
            /*Lisbon*/{4/1,      4/3,       1,       4/4,      4/4},
            /*Madrid*/{4/1,      4/3,       4/4,      1,       4/4},
            /*Warsaw*/{4/1,      4/3,       4/4,      4/4,       1},
    });
    final static PairwiseMatrix matrixNightLife = new PairwiseMatrix(new double[][]{
                    //Venice    Rome     Lisbon    Madrid    Warsaw
            /*Venice*/{1,       2/3,       2/4,      2/4,      2/3},
            /*Rome*/  {3/2,      1,        3/4,      3/4,      3/3},
            /*Lisbon*/{4/2,      4/3,       1,       4/4,      4/3},
            /*Madrid*/{4/2,      4/3,       4/4,      1,       4/3},
            /*Warsaw*/{3/2,      3/3,       3/4,      3/4,       1},
    });
    final static PairwiseMatrix matrixSights = new PairwiseMatrix(new double[][]{
                    //Venice    Rome    Lisbon    Madrid    Warsaw
            /*Venice*/{1,       4/5,      4/5,      4/4,      4/2},
            /*Rome*/  {5/4,      1,       5/5,      5/4,      5/2},
            /*Lisbon*/{5/4,     5/5,       1,       5/4,      5/2},
            /*Madrid*/{4/4,     4/5,      4/5,       1,       4/2},
            /*Warsaw*/{2/4,     2/5,      2/5,      2/4,        1},
    });
    final static PairwiseMatrix matrixMuseums = new PairwiseMatrix(new double[][]{
                    //Venice    Rome    Lisbon  Madrid  Warsaw
            /*Venice*/{1,       4/5,       4/4,      4/4,      4/3},
            /*Rome*/  {5/4,       1,       5/4,      5/4,      5/3},
            /*Lisbon*/{4/4,       4/5,       1,      4/4,      4/3},
            /*Madrid*/{4/4,       4/5,       4/4,      1,      4/3},
            /*Warsaw*/{3/4,       3/5,       3/4,      3/4,      1},
    });
    final static PairwiseMatrix matrixFood = new PairwiseMatrix(new double[][]{
                    //Venice    Rome    Lisbon    Madrid    Warsaw
            /*Venice*/{1,       3/4,      3/4,      3/5,      3/2},
            /*Rome*/  {4/3,      1,       4/4,      4/5,      4/2},
            /*Lisbon*/{4/3,     4/4,       1,       4/5,      4/2},
            /*Madrid*/{5/3,     5/4,      5/4,       1,       5/2},
            /*Warsaw*/{2/3,     2/4,      2/4,      2/5,        1},
    });
}
