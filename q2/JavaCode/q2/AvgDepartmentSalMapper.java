package q2;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AvgDepartmentSalMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
	@Override
	public void map(LongWritable key, Text value, Context con) throws IOException, InterruptedException{
		String ipValue = value.toString();
		String opKey, tempArray[], department;
		Text oppKey = new Text();
		DoubleWritable oppValue = new DoubleWritable();
		tempArray = ipValue.split(",");
		department = getDepartment(tempArray[1]);
		department = department.trim();
		opKey = department + "," + "2000-2010" + "," + tempArray[2];
		oppKey.set(opKey);
		oppValue.set(Double.parseDouble(tempArray[3]));
		con.write(oppKey, oppValue);
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

