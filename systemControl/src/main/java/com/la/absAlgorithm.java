package com.la;

/**
 * @author LA
 * @createDate 2023-03-15-16:04
 */
public class absAlgorithm {
    double x = 4.73;

    public double ad1(double init, double a, double b){

        double y = init;

        double res = Math.abs(y - x);

        int i = 0;
        double pre = y;
        double aft;

        while(res > 0.0001){
            aft = y;
            aft = aft + a;
            double curRes = Math.abs(aft-x);
            System.out.println("第" + (++i) + "次调整: " + aft);
            if(curRes >= res){
                y = pre;
                a = a*b;
            }

            pre = y;
            y = y + a;
            res = Math.abs(y-x);
            System.out.println("第" + (++i) + "次调整: " + y);
        }
        return y;
    }


    public double ad2(double init, double a, double b){

        double y = init;

        double res = Math.abs(y - x);
        double preRes = res;
        double curRes = res;

        int i = 0;
        double pre = y;
        double aft = y;

        while(res > 0.0001){
            aft = aft + a;
            curRes = Math.abs(aft-x);
            System.out.println("第" + (++i) + "次调整: " + aft);
            if(curRes >= res){
                y = pre;
                aft = y;
                res = preRes;
                a = a*b;
            }
            else {
                pre = y;
                y = aft;
                preRes = res;
                res = curRes;
            }

        }
        return y;
    }

    public double ad3(double init, double a, double b){
        double y = init;

        double res = Math.abs(y - x);
        double curRes;

        int i = 0;
        double aft = y;
        int flag = 1;

        while(res > 0.01){
            aft = aft + flag*a;
            curRes = Math.abs(aft-x);
            System.out.println("第" + (++i) + "次调整: " + aft);
            if(curRes >= res){
                flag = -flag;
                aft = y;
                a = a*b;
            }
            else {
                y = aft;
                res = curRes;
            }
        }
        return y;
    }


    public static void main(String[] args) {
        double res1 = new absAlgorithm().ad1(0,0.8,0.5);
        System.out.println("最终结果: " + res1);
        double res2 = new absAlgorithm().ad2(0,0.8,0.5);
        System.out.println("最终结果: " + res2);
        double res3 = new absAlgorithm().ad3(0,4,0.5);
        System.out.println("最终结果: " + res3);
    }

}
