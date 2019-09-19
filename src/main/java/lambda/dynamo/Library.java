/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package lambda.dynamo;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lambda.dynamo.Models.History;
import lambda.dynamo.Models.Task;

import java.util.HashMap;
import java.util.List;

public class Library {
  private DynamoDB dynamoDb;
  private String DYNAMODB_TABLE_NAME = "taskmaster";
  private Regions REGION = Regions.US_WEST_2;

  public Task save(Task task){
    History h = new History("available");
    task.addHistory(h);
    final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();
    DynamoDBMapper ddbMapper = new DynamoDBMapper(ddb);
    ddbMapper.save(task);
    return task;
  }

  public Task updateAssignee(Task task){
    final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();
    DynamoDBMapper ddbMapper = new DynamoDBMapper(ddb);
    Task t = ddbMapper.load(Task.class, task.getId());
    t.setAssignee(task.getAssignee());
    t.setStatus("assigned");
    t.addHistory(new History("assigned"));
    ddbMapper.save(t);
    return t;
  }

  public Task updateState(Task task){
    final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();
    DynamoDBMapper ddbMapper = new DynamoDBMapper(ddb);
    Task t = ddbMapper.load(Task.class, task.getId());
      if(t.getStatus().equals("available")){
        t.setStatus("assigned");
      } else if(t.getStatus().equals("assigned")){
        t.setStatus("accepted");
      } else if(t.getStatus().equals("accepted")){
        t.setStatus("finished");
      }
      t.addHistory(new History(t.getStatus()));
      ddbMapper.save(t);
      return t;
  }

  public List<Task> getAllTasks(){
    final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();
    DynamoDBMapper ddbMapper = new DynamoDBMapper(ddb);
    List<Task> tasks = ddbMapper.scan(Task.class, new DynamoDBScanExpression());
    return tasks;
  }

  public List<Task> getUserTasks(Task task){
    HashMap<String, AttributeValue> eav = new HashMap<>();
    eav.put(":v1", new AttributeValue().withS(task.getAssignee()));
    DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
        .withFilterExpression("assignee = :v1")
        .withExpressionAttributeValues(eav);
    final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();
    DynamoDBMapper ddbMapper = new DynamoDBMapper(ddb);
    List<Task> tasks = ddbMapper.scan(Task.class, scanExpression);

    return tasks;
  }

  public Task deleteTask(Task task){
    final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();
    DynamoDBMapper ddbMapper = new DynamoDBMapper(ddb);
    Task t = ddbMapper.load(Task.class, task.getId());
    ddbMapper.delete(t);
    return t;
  }
}
