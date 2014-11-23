import java.io.*;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class Find extends AbsReadFile {
	
	public int time_tix = 15;

	
	@Override
	public boolean readfile(String filepath) throws FileNotFoundException,
			IOException {
		// TODO Auto-generated method stub
		return super.readfile(filepath);
	}

	@Override
	public void dealWithFile(File file) {
		// TODO Auto-generated method stub
		FileReader fr;
		
			try {
				fr = new FileReader(file);
				BufferedReader reader = new BufferedReader(fr);
				String line;
				
				System.out.println("处理文件"+file.getName());
				
				int t1=0,t2=0,t3=0,t4=0,time=0,nodeID=0;
				double lon,lat;
				String speed;
				while((line = reader.readLine())!=null)
				{
					if("".equals(line.trim()))
					{
						continue;
					}
					//这里因为不同的数据源用的分隔符号不同，有的是tab有的是空格，所以需要注意
					 t1=line.indexOf(' ');
					 t2=line.indexOf(' ',t1+1);
					 t3=line.indexOf(' ',t2+2);
					 t4=line.indexOf(' ',t3+1);
					 time=(int)(Double.valueOf(line.substring(0, t1))+0.5);
					 nodeID=Integer.valueOf(line.substring(t1+1,t2));
					 lon=Double.valueOf(line.substring(t2+1,t3));
					 lat=Double.valueOf(line.substring(t3+1,t4));
					 speed=line.substring(t4+1, line.length());
					 Config.array.add(new Rwp_class(time,nodeID,lon,lat,speed));
					
				}
				
				fr.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void writefile(ArrayList<Rwp_class> array,File file){
		try {
			File fileout = new File(Config.out_file_path+"\\"+file.getName());
			FileWriter fw = new FileWriter(fileout);
			System.out.println("准备输出文件"+fileout.getName());
			for(int id=1;id<=4000;id++){//4000个出租车 逐个计算
				Rwp_class now=new Rwp_class(0,0,0,0,"0");
				Rwp_class next=new Rwp_class(0,0,0,0,"0");
				for(int i=0;i<array.size();i++){
					if(id==((Rwp_class)array.get(i)).nodeID){
						
						if(now.isEmpty()==true)
							now=(Rwp_class)array.get(i);
						else
							next=(Rwp_class)array.get(i);
						System.out.println(now.toString());
						System.out.println(next.toString());
						if(now.time==next.time||next.isEmpty())
							continue;
						else{
							fw.write(now.toString());
							double temp_lon=(next.lon-now.lon)/(next.time-now.time);
							double temp_lat=(next.lat-now.lat)/(next.time-now.time);
							for(int time=now.time+time_tix,t=time_tix;time<next.time;time+=time_tix,t+=time_tix){
								DecimalFormat df=new DecimalFormat(".###");
								String str1=df.format(now.lon+temp_lon*t);
								String str2=df.format(now.lat+temp_lat*t);
								
								fw.write(time+"\t"+now.nodeID+"\t"+str1+"\t"+str2+"\t"+now.speed+"\r\n");
							}
							
							now=next;
						}
					
					}
				}
				fw.write(now.toString());
				double temp_lon=(next.lon-now.lon)/(10800-now.time);//10800是3个小时
				double temp_lat=(next.lat-now.lat)/(10800-now.time);
				for(int time=now.time+time_tix,t=time_tix;time<=10800;time+=time_tix,t+=time_tix){
					DecimalFormat df=new DecimalFormat(".###");
					String str1=df.format(now.lon+temp_lon*t);
					String str2=df.format(now.lat+temp_lat*t);
					
					fw.write(time+"\t"+now.nodeID+"\t"+str1+"\t"+str2+"\t"+now.speed+"\r\n");
				}
				
			}
			fw.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
}
