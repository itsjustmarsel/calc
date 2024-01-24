public enum RomanNumbers {
    I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10), C(100), XC(90), L(50), XL(40);

    private int val;

    RomanNumbers(int val){
        this.val = val;
    }

    int getVal(){
        return val;
    }
}


