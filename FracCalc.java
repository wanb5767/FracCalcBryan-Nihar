
class xmain {
    public static void main(String[] args) {
        String result = produceAnswer("8/4 + 2");
        System.out.println(result);
    }
    // String
    public static String produceAnswer_1(String exp) {
        return exp.split(" ")[2];
    }
    public static int[] stringToInt(String num) {
        int[] rs = new int[3];
        rs[2] = 1; // dominator
        if (num.indexOf("/") == -1) {
            // no fraction, its a integer only
            rs[0] = Integer.parseInt(num);
        } else {
            // num2 has fraction
            if (num.indexOf("_") > 0) {
                // has integer part
                rs[0] = Integer.parseInt(num.split("_")[0]); // take the integer part out
                num = num.split("_")[1]; // keep fraction part
            }
            // now, working with the fraction
            rs[1] = Integer.parseInt(num.split("/")[0]);
            rs[2] = Integer.parseInt(num.split("/")[1]);
        }
        return rs;
    }
    // String
    public static String produceAnswer_2(String exp) {
        String num2 = exp.split(" ")[2];
        int[] rs = stringToInt(num2);
        // now, rs has three elements, 1st for integer, 2nd for numerator, 3rd dominator
        return String.format("whole:%d numerator:%d denominator:%d", rs[0], rs[1], rs[2]);
    }
    // reducing the form of fraction: 9/3 -> 3_0/3
    //  0_8/4 -> 2_0/1
    //  1_6/4 -> 2_1/2
    public static int[] fReduct(int[] num) {
        int[] rs = num;
        rs[0] += rs[1]/rs[2];
        rs[1] %= rs[2];
        if (rs[1] == 0) rs[2] = 1;
        // common factor
        return rs;
    }
    // two key operators: + ; *
    public static int[] fAdd(int[] num1, int[] num2) {
        // num1: 4_2/3 => [4,2,3] 
        // n1 = 4*3+2
        // d1 = 2
        int n1 = num1[0]*num1[2]+num1[1];
        int d1 = num1[2];
        int n2 = num2[0]*num2[2]+num2[1];
        int d2 = num2[2];
        // the summation
        int ntot = n1*d2 + n2*d1;
        int dtot = d1 * d2;
        int[] rs = new int[3];
        rs[0] = ntot / dtot;
        rs[1] = ntot % dtot;
        rs[2] = dtot;
        return rs;
    }
    // two key operators: + ; *
    public static int[] fMul(int[] num1, int[] num2) {
        // turn them into fractions
        int n1 = num1[0]*num1[2]+num1[1];
        int d1 = num1[2];
        int n2 = num2[0]*num2[2]+num2[1];
        int d2 = num2[2];
        // the multiplication: 1/2 * 3/5 = 3/10
        int nmul = n1*n2;
        int dmul = d1*d2;
        int[] rs = new int[3];
        rs[0] = nmul / dmul;
        rs[1] = nmul % dmul;
        rs[2] = dmul;
        return rs;
    }
    public static int[] fDiv(int[] num1, int[] num2) {
        // turn them into fractions
        int n1 = num1[0]*num1[2]+num1[1];
        int d1 = num1[2];
        int n2 = num2[0]*num2[2]+num2[1];
        int d2 = num2[2];
        // the multiplication: 1/2 * 3/5 = 3/10
        int ndiv = n1*d2;
        int ddiv = d1*n2;
        int[] rs = new int[3];
        rs[0] = ndiv / ddiv;
        rs[1] = ndiv % ddiv;
        rs[2] = ddiv;
        return rs;
    }

    public static String produceAnswer(String exp) {
        String num1 = exp.split(" ")[0];
        String operator = exp.split(" ")[1];
        String num2 = exp.split(" ")[2];
        int[] val1 = stringToInt(num1);
        int[] val2 = stringToInt(num2);
        int[] result = new int[3];
        switch (operator) {
            // 1/3 + 2/5 :
            //  numerator = 1*5 + 2*3
            // dominator = 3*5
            case "+":
                result = fAdd(val1, val2);
                break;
            case "-":
                // build -1 in form of array
                int[] minus = new int[]{-1,0,1};
                result = fAdd( val1, fMul(minus,val2) );
                break;
            case "*":
                result = fMul(val1, val2);
                break;
            case "/":
                result = fDiv(val1, val2);
                break;
        }
        result = fReduct(result);
        // now, rs has three elements, 1st for integer, 2nd for numerator, 3rd dominator
        return String.format("whole:%d numerator:%d denominator:%d", result[0], result[1], result[2]);
    }
}