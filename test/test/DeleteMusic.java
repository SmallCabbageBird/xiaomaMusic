package test;

import java.io.File;
import java.util.Scanner;

import org.apache.struts2.components.If;

/**
 * 删除歌曲用
 * @author lenovo
 *
 */
public class DeleteMusic {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println("请输入要删除的文件路径选项");
			System.out.println("1.小马1.0中的music文件夹下的音乐文件");
			System.out.println("2.tomcat服务器中小马3.0的music文件夹下的音乐文件");
			System.out.println("3.退出");
			int choose = sc.nextInt();
			String directory = null; //文件夹目录
			
			
			if(choose == 1){
			
				directory = "D:\\develop\\就业班\\xiaomaMusic-V1.0\\WebRoot\\";
		
			}else if(choose == 2){
			
				directory = "D:\\developtool\\apache-tomcat-7.0.70\\apache-tomcat-7.0.70\\webapps\\ROOT\\";
			
			}else if(choose == 3){
				
				System.out.println("退出系统成功");
				return;
			}else{
				System.out.println("你的输入有误,请重新输入");
				break;
			}
		
			
			System.out.println("请输入要删除的文件名:");
			
			String fileName = sc.next();//文件名
			
			String srcFileName =  directory + fileName;  //全路径名
			
			File file = new File(srcFileName);
			
			if(!file.exists()){
			
				System.out.println("删除文件失败:" + fileName + "不存在");
			
			}else{
				
				String flag = deleteFile(file);
			
				System.out.println(flag);
			}
			
			
		
		}
		
	}
	
	private static String deleteFile(File file){
		
		try {
		
			file.delete();
		
			return "删除成功";
		
		} catch (Exception e) {
		
			e.printStackTrace();
		
			return "删除失败";
		}
	}
}
