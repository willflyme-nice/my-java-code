import java.lang.Thread;

   
public class ThreadTest{
	
	Stack st=new Stack();
	Object obj=new Object();
	Children child=new Children(500);
	Thread child1=new Thread(child,"child1");
	Children chid=new Children(2000);
	Thread child2=new Thread(chid,"child2");
    Children chil=new Children(9000);
	Thread child3=new Thread(chil,"child3");
	
	Parents parents=new Parents(1500);
    Thread father=new Thread(parents,"father");
	Parents parent=new Parents(3000);
	Thread mother=new Thread(parent,"mother");
	
	public static void main(String[]ad){
      
		ThreadTest that=new ThreadTest();
		that.go();
	}
	
	
	void go() {
		father.start();
		mother.start();
		child1.start();
		child2.start();
		child3.start();
		
	}
	
	class Parents implements Runnable{
		int time;
		Parents(int t){
			time=t;
		}
		public void run() {
			while(true) {
				synchronized(st) {
					try{
						//if(st.getNumber()==5)   //最多只能放5个
						 //  obj.wait();
					    st.push();
					    st.notify();
					    System.out.println(Thread.currentThread().getName()
							+"放下了一个******************还剩："+st.getNumber());
					
						Thread.sleep(time);
					}catch(InterruptedException e) {}
				}
			}
					
				
		}
	}
	
	class Children implements Runnable {
		int time;
		 Children(int t){
			 time=t;
		 }
		public void run() {
			while(true) {
				synchronized(st) {
					try{
						if(st.getNumber()==0)   //盘子空了
						    st.wait();
					    st.pop();
					  //  obj.notify();
					    System.out.println(Thread.currentThread().getName()
							+"拿走了一个******************还剩："+st.getNumber());
					
						Thread.sleep(time);
					}catch(InterruptedException e) {}
				}
			}
			
		}
	}

	
}




class Stack{
	int idx=0;
	char ch[]=new char[10];
	public void push() {
			ch[idx]='a';
		    idx++;
	}
	
	public char pop() {
			idx--;
		    return ch[idx];

	}
	public int getNumber() {
		return idx;
	}
	
}
















