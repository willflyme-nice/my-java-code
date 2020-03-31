import java.text.*;
public class ProgramTimes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimeThread tt=new TimeThread();
		tt.start();
	}
	public static double rounds(double dou) {
		long lon=Math.round(dou*100);
		double result=(double)lon/100;
		return result;			
	}
	//��ʱ��ת��Ϊһ����ʽ���ַ���
	public static String sringOfTime(long time) {
		String timeString=null;
		double secondtime=rounds(time/1000.0);
		if(secondtime>=0) {
			timeString=rounds(time%60000/1000.0)+"��";
		}
		if(secondtime>=60) {
			int minute=(int)secondtime%3600/60;
			timeString=Integer.toString(minute)+"��"+timeString;
		}
		if(secondtime>=3600) {
			int hour=(int)secondtime/3600;
			timeString=Integer.toString(hour)+"ʱ"+timeString;
		}
		timeString="�������У�"+timeString;
		
		return timeString;
	}
	
	static class TimeThread extends Thread{
		public void run() {
			while(true) {
				long lon=System.currentTimeMillis();
				String timeText=sringOfTime(lon);
				System.out.println(timeText);
				
				try {
					Thread.sleep(2231);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}

}
