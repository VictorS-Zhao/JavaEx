
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
 
public class ArrayBlockingCommunication {
 
    public static void main(String[] args) {
         
        final Business business = new Business();
         
        new Thread(new Runnable(){
 
            public void run() {
                for(int i=0; i<50; i++){
                    business.sub(i);
                }
            }
             
        }).start();
         
         
        new Thread(new Runnable(){
 
            public void run() {
                for(int i=0; i<50; i++){
                    business.main(i);
                }
            }
             
        }).start();
         
    }
     
     
     
    static class Business{
         
        BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<Integer>(1);
        BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<Integer>(1);
 
        {
            try{
                queue2.put(1);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
         
        public void sub(int i){
             
             
            try {
                queue1.put(1);
                System.out.println("线程" + Thread.currentThread().getName() + 
                "正在阻塞");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("线程" + Thread.currentThread().getName() + 
            "开始运行");
            for(int j=1; j<=10; j++){
                System.out.println("sub thread sequence is " + j + " loop of " + i);
            }
            try {
                queue2.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
         
         
         
        public void main(int i){
             
            try {
                queue2.put(1);
                System.out.println("线程" + Thread.currentThread().getName() + 
                        "正在阻塞");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程" + Thread.currentThread().getName() + 
            "开始运行");
            for(int j=1; j<=10; j++){
                System.out.println("main thread sequence is " + j + " loop of " + i);
            }
            try {
                queue1.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
 
}

