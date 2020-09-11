import java.util.concurrent.TimeUnit;

public class threadHomework {
    //    .多线程(
//    喝茶(洗茶杯(5个茶杯*2s)、烧水5s)，
//    洗完第三个茶杯时突然被叫出去开门，立刻通知烧水线程，烧水线程收到通知，立马结束烧水)
    private static volatile int number = 0;// 设置内存共享变量，标志位：当前第几个杯子在被洗
    public static void main(String[] args) throws InterruptedException{
        //烧水线程
        new Thread(()->{
            long start = System.currentTimeMillis();
            while(true) {
                long end = System.currentTimeMillis();
                if (number == 3 || end-start == 5000) { //当第三个洗完的时候  结束线程
                    System.out.println("烧水线程已结束");
                    break;
                }
            }
        }).start();
        //洗茶杯线程
        for(int i = 0;i<5;i++){  //创建五个线程，并行洗杯子
            int cup = i;
            new Thread(()->{
                System.out.println("开始洗" + cup + "号茶杯");
                try {
                    TimeUnit.SECONDS.sleep(2);   //洗杯子需要两秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(cup + "号茶杯已经洗完");  //洗完标志
                number = cup;                     //洗完之后变更内存共享的标志位
            }).start();
            TimeUnit.SECONDS.sleep(1);   //每个洗杯子线程启动之后相隔一秒启动下一个线程，不然太快了，并发难以控制。
        }
    }
}
