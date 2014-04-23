package reports;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;

public class QuartReports extends Application
{
	public void start(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Quarterly Revenue");
		stage.setWidth(500);
		stage.setHeight(500);

		ObservableList<PieChart.Data> pieChartData =
				FXCollections.observableArrayList(
						new PieChart.Data("First Quarter", 25),
						new PieChart.Data("Second Quarter", 25),
						new PieChart.Data("Third Quarter", 25),
						new PieChart.Data("Fourth Quarter", 25));
		final PieChart chart = new PieChart(pieChartData);
		chart.setTitle("Quarterly Revenue");

		((Group) scene.getRoot()).getChildren().add(chart);
		stage.setScene(scene);
		stage.show();



	}
	public void launchReport()
	{
		QuartReports.launch();
	}

}