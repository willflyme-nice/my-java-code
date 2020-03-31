package clockparts;

public class HandHour {
	private int angle; //当前角度
	
	//向前走一角度
	public void singleStepAhead() {
		angle++;
		if(angle==360)
			angle=0;
	}

	//向后走一角度
	public void singleStepBack() {
		angle--;
		if(angle==-1)
			angle=359;
	}
	
	//获取当前角度
	public int getAngle() {
		return this.angle;
	}
	
	//设置角度
	public void setAngle(int angle) {
		this.angle = angle;
	}
}
