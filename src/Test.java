
import ustcadc.USTCADC;
import ustcdac.USTCDAC;

public class Test
{
	public static void main(String args[])
	{
		
		String info = USTCADC.getDriverInfo();
		System.out.println(info);
		USTCADC ad = new USTCADC("68-05-CA-47-45-9A","00-00-00-00-00-01");
		ad.openADC();
		ad.setTrigCount(10);
		ad.closeADC();
				
		info = USTCDAC.getDriverInfo();
		System.out.println(info);
		USTCDAC da = new USTCDAC("10.0.2.5",(short)80);
		da.openDAC();
		da.startStop(240);
		
		double temp = da.getChipTemperature(1);
		System.out.println(temp);
		
		int []data = new int[32768];
		for(int k=0;k<32768;k++) {
			data[k] = k*2;
		}
		long []seq = new long[4096];
		for(int k=0;k<seq.length;k++) {
			seq[k] = 0x0000000010000000l;
		}
		da.setIsBlock(true);
		for(int k=1;k <= 4; k++) {
			da.setDefaultVolt(k, (short)0);
			da.writeWave(k, 0, data);
			da.writeSeq(k, 0, seq);
		}
		
		da.startStop(15);
		da.closeDAC();
	}
}