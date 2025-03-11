package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pageobject.TodoPage;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class TodoStepDefinitions {
    WebDriver driver;
    TodoPage todoPage;
    public TodoStepDefinitions(){
        this.driver = Hooks.driver;
        todoPage = new TodoPage(this.driver);
    }
    @Given("^The todo page is showing$")
    public void the_todo_page_is_showing() {
        this.todoPage.Open();
    }

    @When("^The user attempt to add items as below$")
    public void the_user_attempt_to_add_items_as_below(DataTable tasks) {
        List<Map<String, String>> lstTasks = tasks.asMaps(String.class, String.class);
        for(Map<String, String> t:lstTasks){
            todoPage.TaskInput.clear();
            todoPage.TaskInput.sendKeys(t.get("Task"));
            todoPage.TaskInput.sendKeys(Keys.ENTER);
        }
    }

    @When("^The user attempt to remove \"([^\"]*)\" item from list$")
    public void the_user_attempt_to_remove_something_item_from_list(String item2Remove) {
        this.todoPage.RemoveTask(item2Remove);
    }

    @When("^The user attempt to make \"([^\"]*)\" task is completed$")
    public void the_user_attempt_to_make_something_task_is_completed(String item2Complete) {
        this.todoPage.CompleteTask(item2Complete);
    }

    @Then("^The list of tasks is display as below order$")
    public void the_list_of_tasks_is_display_as_below_order(DataTable tasks) throws InterruptedException {
        List<Map<String, String>> lstTasks = tasks.asMaps(String.class, String.class);
        int index=0;
        boolean isMatched = true;
        for(Map<String, String> t:lstTasks){
            if(!t.get("Task").equalsIgnoreCase(todoPage.TaskItems.get(index).getText()))
            {
                isMatched=false;
            }
            index++;
        }

        Assert.assertTrue("The list of Task display as expected order", isMatched);

        Thread.sleep(2000);
    }

    @Then("^The list of active tasks is display as below order$")
    public void the_list_of_active_tasks_is_display_as_below_order(DataTable tasks) throws InterruptedException {
        this.todoPage.ActiveTab.click();
        this.todoPage.reInitElement();

        List<Map<String, String>> lstTasks = tasks.asMaps(String.class, String.class);
        int index=0;
        boolean isMatched = true;
        for(Map<String, String> t:lstTasks){
            if(!t.get("Active").equalsIgnoreCase(todoPage.TaskItems.get(index).getText()))
            {
                isMatched=false;
            }
            index++;
        }

        Assert.assertTrue("The list of Active Tasks display as expected order", isMatched);
        Thread.sleep(2000);
    }

    @And("^The user try to refresh webpage$")
    public void the_user_try_to_refresh_webpage() {
        this.driver.navigate().refresh();
    }

    @And("^The below tasks are added to list$")
    public void the_below_task_is_added_to_list(DataTable tasks) {
        List<Map<String, String>> lstTasks = tasks.asMaps(String.class, String.class);
        for(Map<String, String> t:lstTasks){
            todoPage.TaskInput.clear();
            todoPage.TaskInput.sendKeys(t.get("Task"));
            todoPage.TaskInput.sendKeys(Keys.ENTER);
        }
    }

    @And("^The list of completed is display as below order$")
    public void the_list_of_completed_is_display_as_below_order(DataTable tasks) throws InterruptedException {
        this.todoPage.CompleteTab.click();
        Thread.sleep(1000);
        this.todoPage.reInitElement();

        List<Map<String, String>> lstTasks = tasks.asMaps(String.class, String.class);
        int index=0;
        boolean isMatched = true;
        for(Map<String, String> t:lstTasks){
            if(!t.get("Completed").equalsIgnoreCase(todoPage.TaskItems.get(index).getText()))
            {
                isMatched=false;
            }
            index++;
        }

        Assert.assertTrue("The list of Completed Task display as expected order", isMatched);
        Thread.sleep(2000);
    }

    @When("^The user attempt to change \"([^\"]*)\" to \"([^\"]*)\"$")
    public void the_user_attempt_to_change_something_to_something(String oldTask, String newTask) {
        this.todoPage.EditTask(oldTask, newTask);
    }
}
