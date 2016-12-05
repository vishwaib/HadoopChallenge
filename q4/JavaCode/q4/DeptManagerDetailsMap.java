package q4;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DeptManagerDetailsMap extends Mapper<LongWritable, Text, Text, IntWritable> {
	@Override
	public void map(LongWritable key, Text value, Context con) throws IOException, InterruptedException{		
		Text opKey = new Text();
		IntWritable opValue = new IntWritable(1);		
		String ipValue, oppKey, tempArray[], department;
		ipValue = value.toString();
		tempArray = ipValue.split(",");
		department = getDepartment(tempArray[0]);
		oppKey = tempArray[0] + "," + department + "," + tempArray[2] + "_" + tempArray[3]+ "!";		
		opKey.set(oppKey);		
		con.write(opKey, opValue);
	}
	
	public String getDepartment(String key){
		String valToReturn = "";
		switch(key){
			case "d009":
				valToReturn = "Customer Service";
			break;
			case "d005":
				valToReturn = "Development";
			break;
			case "d002":
				valToReturn = "Finance";
			break;
			case "d003":
				valToReturn = "Human Resources";
			break;
			case "d001":
				valToReturn = "Marketing";
			break;
			case "d004":
				valToReturn = "Production";
			break;
			case "d006":
				valToReturn = "Quality Management";
			break;
			case "d008":
				valToReturn = "Research";
			break;
			case "d007":
				valToReturn = "Sales";
			break;
		}
		return valToReturn;
	}		
}
