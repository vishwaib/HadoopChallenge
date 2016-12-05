package q3;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class EmpGrowthMap  extends Mapper<LongWritable, Text, Text, Text> {
	@Override
	public void map(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
		Text finalopKey = new Text();
		Text finalopValue = new Text();
		String department = "", ipValue, ipValueArray[];
		String opKey, opValue;
		ipValue = value.toString();
		ipValueArray = ipValue.split(",");
		opKey = ipValueArray[0] + "," + ipValueArray[1] + " " + ipValueArray[2];
		department = getDepartment(ipValueArray[4].trim());
		opValue = ipValueArray[3] + "," + department;
		finalopKey.set(opKey);
		finalopValue.set(opValue);
		con.write(finalopKey, finalopValue);
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
