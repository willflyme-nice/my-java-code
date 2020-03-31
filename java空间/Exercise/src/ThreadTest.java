
public class ThreadTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName()+"正在运行");
		ABC abc=new ABC();
		Thread thread1=new Thread(abc,"线程1");
		Thread thread2=new Thread(abc,"线程2");
		thread1.start();
		thread2.start();
	}

}

class ABC implements Runnable{
	int i;
	int j=0;
	public void run() {
		
		for(i=0;i<20;i++)
		{
			synchronized (this){
				System.out.println(Thread.currentThread().getName()+"正在运行"+j++);
			}
			try {
				Thread.currentThread().sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	
}


