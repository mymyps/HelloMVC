package common.fileRename;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File fo) {
		
		File newFile = null;
		do {
			// rename을 위한 준비값
			long currentTime = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
			int  rndNum = (int)(Math.random()*10000);
			
			// 파일 확장자 가져오기
			// rename시에 확장자까지 변경하려면 안됨
			String oldName = fo.getName(); //fo : 파라미터값
			String ext = "";
			int dot = oldName.indexOf(".");
			
			if(dot > -1) {
				ext = oldName.substring(dot);
			}
//			System.out.println("oldname : " + oldName);
//			System.out.println("rndNum : " + rndNum);
//			System.out.println("ext : " + ext);
//			System.out.println("sdf : " + sdf.format(new Date(currentTime)));
			
			String newName = sdf.format(new Date(currentTime)) + "_" + rndNum + ext;
//			System.out.println("newName : " + newName);
			
//			String newName = sdf.format(new Date(currenTime)+"_"+rndNum+ext);
			newFile = new File(fo.getParent(), newName);
			
		}while( !createNewFile(newFile) );
		
		return newFile;
	}
	
	private boolean createNewFile(File newFile) {
		// 파일이 있는지 확인하고 있으면 생성하지 않고 다른이름으로 변경
		try {
			return newFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
