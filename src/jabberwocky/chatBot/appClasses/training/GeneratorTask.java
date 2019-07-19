package jabberwocky.chatBot.appClasses.training;

import java.util.ArrayList;

import jabberwocky.chatBot.ServiceLocator;
import jabberwocky.chatBot.appClasses.App_Model;
import jabberwocky.chatBot.appClasses.TrainedData;
import jabberwocky.chatBot.appClasses.dataClasses.TrainingUnit;
import javafx.concurrent.Task;

public class GeneratorTask extends Task<String> {
	private TrainedData trainedData;
	ServiceLocator serviceLocator;

	public GeneratorTask(App_Model model, ArrayList<TrainingUnit> sentence) {
		super();
		serviceLocator = ServiceLocator.getServiceLocator();
		this.trainedData = new TrainedData(serviceLocator.getSequenceLength());
	}

	@Override
	protected String call() throws Exception {
		// TODO: Find least-common word in user entry
//		int frequency = Integer.MAX_VALUE;
//		for (TrainingUnit tu : sentence) {
//			int tu_freq = 
//		}
		
		
		// TODO: Generate back to BOF
		// TODO: Generate forward to EOF
		return "asdf";
	}
	
	
}
