package clockparts;

public class HandHour {
	private int angle; //��ǰ�Ƕ�
	
	//��ǰ��һ�Ƕ�
	public void singleStepAhead() {
		angle++;
		if(angle==360)
			angle=0;
	}

	//�����һ�Ƕ�
	public void singleStepBack() {
		angle--;
		if(angle==-1)
			angle=359;
	}
	
	//��ȡ��ǰ�Ƕ�
	public int getAngle() {
		return this.angle;
	}
	
	//���ýǶ�
	public void setAngle(int angle) {
		this.angle = angle;
	}
}
