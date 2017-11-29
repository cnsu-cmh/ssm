package com.xiaoshu.job;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.xiaoshu.entity.Attachment;
import com.xiaoshu.entity.Log;
import com.xiaoshu.service.AttachmentService;
import com.xiaoshu.service.LogService;
import com.xiaoshu.util.TimeUtil;


public class JobUtil {
	
	@Autowired
	private AttachmentService  attachmentService;
	@Autowired
	private LogService  logService;
	
	/**
	 * 定时器
	 * 每周三自动备份操作记录日志
	 */
	@Scheduled(cron = "0 0 2 ? * WED")   // 星期三晚上2点
	public void backup(){
		try {
			String time = TimeUtil.formatTime(new Date(), "yyyyMMddHHmmss");
		    String excelName = "自动备份"+time;
			Log log = new Log();
			log.setCreatetime(new Date());
			List<Log> list = logService.findLog(log);
			String[] handers = {"序号","操作人","IP地址","操作时间","操作模块","操作类型","详情"};
			// 1导入硬盘
			ExportExcelToDisk(handers,list, excelName);
			// 2导出的位置放入attachment表
			Attachment attachment = new Attachment();
			attachment.setAttachmentname(excelName+".xls");
			attachment.setAttachmentpath("logs/backup");
			attachment.setAttachmenttime(new Date());
			attachmentService.insertAttachment(attachment);
			// 3删除log表
			logService.truncateLog();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	// 导出到硬盘
	@SuppressWarnings("resource")
	private void ExportExcelToDisk(String[] handers, List<Log> list, String excleName) {
		
		try {
			HSSFWorkbook wb = new HSSFWorkbook();//创建工作簿
			HSSFSheet sheet = wb.createSheet("操作记录备份");//第一个sheet
			HSSFRow rowFirst = sheet.createRow(0);//第一个sheet第一行为标题
			rowFirst.setHeight((short) 500);
			for (int i = 0; i < handers.length; i++) {
				sheet.setColumnWidth((short) i, (short) 4000);// 设置列宽
			}
			//写标题了
			for (int i = 0; i < handers.length; i++) {
			    //获取第一行的每一个单元格
			    HSSFCell cell = rowFirst.createCell(i);
			    //往单元格里面写入值
			    cell.setCellValue(handers[i]);
			}
			for (int i = 0;i < list.size(); i++) {
			    //获取list里面存在是数据集对象
			    Log log = list.get(i);
			    //创建数据行
			    HSSFRow row = sheet.createRow(i+1);
			    //设置对应单元格的值
			    row.setHeight((short)400);   // 设置每行的高度
			    //"序号","操作人","IP地址","操作时间","操作模块","操作类型","详情"
			    row.createCell(0).setCellValue(i+1);
			    row.createCell(1).setCellValue(log.getUsername());
			    row.createCell(2).setCellValue(log.getIp());
			    row.createCell(3).setCellValue(TimeUtil.formatTime(log.getCreatetime(), "yyyy-mm-dd hh:mm:ss"));
			    row.createCell(4).setCellValue(log.getOperation());
			    row.createCell(5).setCellValue(log.getModule());
			    row.createCell(6).setCellValue(log.getContent());
			}
			//写出文件（path为文件路径含文件名）
				OutputStream os;
				String path = this.getClass().getClassLoader().getResource("/").getPath();
				os = new FileOutputStream(new File(path.substring(1,path.length()-16)+"logs"+File.separator+"backup"+File.separator+excleName+".xls"));
				wb.write(os);
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	

}
