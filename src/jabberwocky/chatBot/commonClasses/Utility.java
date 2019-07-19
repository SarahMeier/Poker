package jabberwocky.chatBot.commonClasses;

import java.util.ArrayList;

import jabberwocky.chatBot.appClasses.dataClasses.BOF_Unit;
import jabberwocky.chatBot.appClasses.dataClasses.EOF_Unit;
import jabberwocky.chatBot.appClasses.dataClasses.PunctuationUnit;
import jabberwocky.chatBot.appClasses.dataClasses.TrainingUnit;
import jabberwocky.chatBot.appClasses.dataClasses.WordUnit;

public class Utility {
	public static <T> void reverse(ArrayList<T> list) {
		for (int i = 0; i < list.size()/2; i++) {
			int j = list.size() - i - 1;
			T tmp = list.get(i);
			list.set(i, list.get(j));
			list.set(j, tmp);
		}
	}
	
	public static ArrayList<TrainingUnit> parseSentence(StringBuffer data) {
		ArrayList<TrainingUnit> sentence = new ArrayList<>();
		sentence.add(BOF_Unit.BOF);
		boolean endOfSentence = false;
		while (!endOfSentence && data.length() > 0) {
			TrainingUnit tu = parseTrainingUnit(data);
			if (tu.getClass() == PunctuationUnit.class) {
				PunctuationUnit pu = (PunctuationUnit) tu;
				endOfSentence = pu.isEndOfSentence();
			}
			sentence.add(tu);
		}
		sentence.add(EOF_Unit.EOF);		
		return sentence;
	}

	/**
	 * Parse one unit from the beginning of the StringBuffer, removing the parsed
	 * data.
	 */
	private static TrainingUnit parseTrainingUnit(StringBuffer sb) {
		TrainingUnit unit;
		
		if (PunctuationUnit.isPunctuation(sb.charAt(0))) {
			// If next character is punctuation, then it is the next training unit
			char c = sb.charAt(0);
			unit = new PunctuationUnit(c);
			sb.delete(0,  1);
		} else {
			// Parse word to the next punctuation, space or end-of-line character.
			boolean found = false;
			int pos = 0;
			int end = -1;
			while (!found && pos < sb.length()) {
				if (sb.charAt(pos) == ' ' || PunctuationUnit.isPunctuation(sb.charAt(pos))) {
					end = pos;
					found = true;
				}
				pos++;
			}
			if (end >= 0) { // Found word
				unit = new WordUnit(sb.substring(0, end));
				sb.delete(0, end);
				// if the next character is a space, discard it
				if (sb.charAt(0) == ' ') sb.delete(0,  1);
			} else { // This is the last word in the StringBuffer
				unit = new WordUnit(sb.toString());
				sb.delete(0, sb.length());
			}
		}
		return unit;
	}	
}
