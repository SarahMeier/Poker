package jabberwocky.chatBot.appClasses.training;

import java.util.ArrayList;
import java.util.HashMap;

import jabberwocky.chatBot.ServiceLocator;
import jabberwocky.chatBot.appClasses.App_Model;
import jabberwocky.chatBot.appClasses.TrainedData;
import jabberwocky.chatBot.appClasses.dataClasses.BOF_Unit;
import jabberwocky.chatBot.appClasses.dataClasses.PunctuationUnit;
import jabberwocky.chatBot.appClasses.dataClasses.EOF_Unit;
import jabberwocky.chatBot.appClasses.dataClasses.HashEntry;
import jabberwocky.chatBot.appClasses.dataClasses.Sequence;
import jabberwocky.chatBot.appClasses.dataClasses.TrainingUnit;
import jabberwocky.chatBot.appClasses.dataClasses.WordUnit;
import jabberwocky.chatBot.commonClasses.Utility;
import javafx.concurrent.Task;

public class TrainerTask extends Task<Void> {
	private TrainedData trainedData;
	private StringBuffer data; // The data to train on
	ServiceLocator serviceLocator;

	public TrainerTask(App_Model model, StringBuffer data) {
		super();
		serviceLocator = ServiceLocator.getServiceLocator();
		this.trainedData = new TrainedData(serviceLocator.getSequenceLength());
		model.setFoundationData(trainedData);
		this.data = data;
		serviceLocator.getLogger().info("Trainer task created");
	}
	
	@Override
	protected Void call() throws Exception {
		int maxProgress = 3 + serviceLocator.getSequenceLength();
		preprocessData(data); // Remove excess whitespace
		this.updateProgress(1, maxProgress);
		
		ArrayList<ArrayList<TrainingUnit>> sentences = parseSentences(data);
		this.updateProgress(2, maxProgress);
		
		calculateWordFrequencies(sentences, trainedData.wordFrequencies);
		this.updateProgress(3, maxProgress);
		
		for (int sequenceLength = 1; sequenceLength <= serviceLocator.getSequenceLength(); sequenceLength++) {
			HashMap<String, ArrayList<HashEntry>> forwardHashEntries = trainedData.forwardSequences.get(sequenceLength-1);
			HashMap<String, ArrayList<HashEntry>> reverseHashEntries = trainedData.forwardSequences.get(sequenceLength-1);
			for (ArrayList<TrainingUnit> sentence : sentences) {
				processSentence(sentence, forwardHashEntries, sequenceLength);
				Utility.reverse(sentence);
				processSentence(sentence, reverseHashEntries, sequenceLength);
			}			
			this.updateProgress(sequenceLength+3, maxProgress);
		}
		return null;
	}
	
	/**
	 * Move through the sequence from start-to-finish, adding entries to the given hash-table. We initially start with 
	 * @param sentence
	 * @param sequence
	 * @param sequenceLength
	 */
	private void processSentence(ArrayList<TrainingUnit> sentence, HashMap<String, ArrayList<HashEntry>> hashEntries, int sequenceLength) {
		int pos = 0;
		Sequence sequence = new Sequence(sentence.get(pos++));
		while (pos < sentence.size()) {
			TrainingUnit tu = sentence.get(pos++);
			trainOneUnit(hashEntries, sequence, tu);
			sequence.addUnit(tu, serviceLocator.getSequenceLength());
		}
		trainOneUnit(hashEntries, sequence, EOF_Unit.EOF);
	}
	
	private void calculateWordFrequencies(ArrayList<ArrayList<TrainingUnit>> sentences, HashMap<WordUnit, Integer> frequencies) {
		for (ArrayList<TrainingUnit> sentence : sentences) {
			for (TrainingUnit tu : sentence) {
				if (tu.getClass() == WordUnit.class) {
					WordUnit wu = (WordUnit) tu;
					int oldCount = frequencies.containsKey(wu) ? frequencies.get(wu) : 0;
					frequencies.put(wu, oldCount+1);
				}
			}
		}
	}
	
	private ArrayList<ArrayList<TrainingUnit>> parseSentences(StringBuffer data) {
		ArrayList<ArrayList<TrainingUnit>> sentences = new ArrayList<>();

		while (data.length() > 0) {
			ArrayList<TrainingUnit> sentence = Utility.parseSentence(data);
			sentences.add(sentence);
		}
		return sentences;
	}
	
	/**
	 * Clean input data in the given StringBuffer by:
	 * - Removing any whitespace at the start or end of the file.
	 * - Removing doubled whitespace characters
	 * - Removing windows-style line-breaks
	 * - Replacing fancy quotes and apostrophes with ASCII characters
	 */
	private void preprocessData(StringBuffer sb) {
		// Remove whitespace at start and end
		while (sb.length() > 0 && sb.charAt(0) <= 0x20)
			sb.deleteCharAt(0);
		while (sb.length() > 0 && sb.charAt(sb.length() - 1) <= 0x20)
			sb.deleteCharAt(sb.length() - 1);

		// Remove doubled whitespace characters
		for (int pos = 0; pos < sb.length() - 1; pos++) {
			while (sb.length() > 1 && sb.charAt(pos) <= 0x20 && sb.charAt(pos) == sb.charAt(pos + 1))
				sb.deleteCharAt(pos + 1);
		}
		
		// Remove Windows-style linebreaks (delete ^M characters)
		for (int pos = sb.length()-1; pos >= 0; pos--) {
			if (sb.charAt(pos) == 0x0013) sb.deleteCharAt(pos);
		}
		
		// Replace fancy quotation marks and apostrophes with ASCII
		for (int pos = 0; pos < sb.length() - 1; pos++) {
			// Apostrophes of various sorts
			if (sb.charAt(pos) == 0x2018) sb.replace(pos, pos+1, "'");
			if (sb.charAt(pos) == 0x2019) sb.replace(pos, pos+1, "'");
			if (sb.charAt(pos) == 0x201A) sb.replace(pos, pos+1, "'");
			if (sb.charAt(pos) == 0x201B) sb.replace(pos, pos+1, "'");
			
			// Quotation marks of various sorts
			if (sb.charAt(pos) == 0x00AB) sb.replace(pos, pos+1, "\"");
			if (sb.charAt(pos) == 0x00BB) sb.replace(pos, pos+1, "\"");
			if (sb.charAt(pos) == 0x201C) sb.replace(pos, pos+1, "\"");
			if (sb.charAt(pos) == 0x201D) sb.replace(pos, pos+1, "\"");
			if (sb.charAt(pos) == 0x201E) sb.replace(pos, pos+1, "\"");
			if (sb.charAt(pos) == 0x201F) sb.replace(pos, pos+1, "\"");
			
			// Hyphens of various sorts
			if (sb.charAt(pos) == 0x2010) sb.replace(pos, pos+1, "-");
			if (sb.charAt(pos) == 0x2011) sb.replace(pos, pos+1, "-");
			
			// Spaces of various sorts
			if (sb.charAt(pos) == 0x00A0) sb.replace(pos, pos+1, " ");
			if (sb.charAt(pos) >= 0x2002 && sb.charAt(pos) <= 0x200B) sb.replace(pos, pos+1, " ");
			if (sb.charAt(pos) == 0x202F) sb.replace(pos, pos+1, " ");
		}		
	}	
	
	/**
	 * Add one unit to the training data
	 */
	private void trainOneUnit(HashMap<String, ArrayList<HashEntry>> hashEntries, Sequence sequence, TrainingUnit c) {
		ArrayList<HashEntry> hashEntriesThisSequence = getHashEntries(hashEntries, sequence);
		boolean found = false;
		for (HashEntry entry : hashEntriesThisSequence) {
			if (entry.getFollowingUnit().equals(c)) {
				entry.incrementQuantity();
				found = true;
				break;
			}
		}
		if (!found) {
			hashEntriesThisSequence.add(new HashEntry(1, c));
		}
	}

	/**
	 * This method returns the list of HashEntries for the sequence given.
	 */
	private ArrayList<HashEntry> getHashEntries(HashMap<String, ArrayList<HashEntry>> hashEntries, Sequence sequence) {
		ArrayList<HashEntry> hashEntriesThisSequence = hashEntries.get(sequence.toString());
		if (hashEntriesThisSequence == null) {
			hashEntriesThisSequence = new ArrayList<>();
			hashEntries.put(sequence.toString(), hashEntriesThisSequence);
		}
		return hashEntriesThisSequence;
	}
}
