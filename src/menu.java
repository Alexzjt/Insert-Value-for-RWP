import java.io.*;


public class menu {
	public static void main(String args[]){
		String outpath=Config.out_file_path;
		Find f=new Find();
		File file=new File(Config.in_file_path);
		f.dealWithFile(file);
		f.writefile(Config.array, file);
	}
}
