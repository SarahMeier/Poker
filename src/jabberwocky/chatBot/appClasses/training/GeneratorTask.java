package jabberwocky.chatBot.appClasses.training;

import java.util.ArrayList;

import jabberwocky.chatBot.ServiceLocator;
import jabberwocky.chatBot.appClasses.App_Model;
import jabberwocky.chatBot.appClasses.TrainedData;
import jabberwocky.chatBot.appClasses.dataClasses.TrainingUnit;
import javafx.concurrent.Task;

public class GeneratorTask extends Task<String> {
	private TrainedData trainedData;
	private ServiceLocator serviceLocator;
	private ArrayList<TrainingUnit> sentence;

	public GeneratorTask(App_Model model, ArrayList<TrainingUnit> sentence) {
		super();
		serviceLocator = ServiceLocator.getServiceLocator();
		this.trainedData = model.getFoundationData();
		this.sentence = sentence;
	}

	@Override
	protected String call() throws Exception {
		// TODO: Find least-common word in user entry
		int frequency = Integer.MAX_VALUE;
		TrainingUnit key_tu = null;
		for (TrainingUnit tu : sentence) {
			Integer tu_freq = trainedData.wordFrequencies.get(tu);
			if (tu_freq != null && tu_freq < frequency) {
				frequency = tu_freq;
				key_tu = tu;
			} else {
				serviceLocator.getLogger().fine("Word '" + tu + "' not found in training data");
			}
		}
		
		System.out.println("Keyword = " + key_tu + " which occurs " + frequency + " times");
		// TODO: Generate back to BOF
		// TODO: Generate forward to EOF
		return "asdf";
	}
	
	
}
