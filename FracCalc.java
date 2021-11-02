
class xmain {
    public static void main(String[] args) {
        String result = produceAnswer("1_2/3 + 5_3/6");
        System.out.println(result);
    }
    // String
    public static String produceAnswer_1(String exp) {
        return exp.split(" ")[2];
    }
    public static int[] stringToInt(String num) {
        int[] rs = new int[3];
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
    public static String produceAnswer(String exp) {
        String num1 = exp.split(" ")[0];
        String operator = exp.split(" ")[1];
        String num2 = exp.split(" ")[2];
        int[] rs1 = stringToInt(num1);
        int[] rs2 = stringToInt(num2);
        int[] fin = new int[3];
        switch (operator) {
            // 1/3 + 2/5 :
            //  numerator = 1*5 + 2*3
            // dominator = 3*5
            case "+":
                fin[0] = rs1[0] + rs2[0];
                fin[1] = rs1[1]*rs2[2] + rs2[1]*rs1[2];
                fin[2] = rs1[2]*rs2[2];
                break;
            case "-":
                break;
            case "*":
                break;
            case "/":
                break;
        }
        // now, rs has three elements, 1st for integer, 2nd for numerator, 3rd dominator
        return String.format("whole:%d numerator:%d denominator:%d", fin[0], fin[1], fin[2]);
    }
}
