
import java.util.ArrayList;
import java.util.Collections;

public class Trial {
	
	private int NUMBER_OF_TRIALS = 36;
	
	private int[] m_aEntries = new int[NUMBER_OF_TRIALS];
	private long[] m_aResponseTimers = new long[NUMBER_OF_TRIALS];
	private long[] m_aReactionTimers = new long [NUMBER_OF_TRIALS];
	private float[] m_aPoints = new float[NUMBER_OF_TRIALS];
	private long m_iFastestResponseTime;
	private long m_iFastestReactionTime;

	Trial() {
		for (int i = 0; i < m_aEntries.length; i++) {
			m_aResponseTimers[i] = 0;
		}
		
		for (int i = 0; i < m_aEntries.length; i++) {
			m_aReactionTimers[i] = 0;
		}
		
		for (int i = 0; i < m_aPoints.length; i++) {
			m_aPoints[i] = 0;
		}

		GenerateTrials();
	}

	private void GenerateTrials() {
		ArrayList<Integer> EntryNumbers = new ArrayList<Integer>();

		for (int i = 0; i < NUMBER_OF_TRIALS; i++)
			EntryNumbers.add(i);

		Collections.shuffle(EntryNumbers);

		for (int i = 0; i < NUMBER_OF_TRIALS; i++)
			m_aEntries[i] = EntryNumbers.get(i);
	}

	public void setResponseTimer(int step, long responseTime) {
		m_aResponseTimers[step] = responseTime;

		if (step == NUMBER_OF_TRIALS - 1) {
			m_iFastestResponseTime = m_aResponseTimers[0];
			for (int i = 1; i < m_aResponseTimers.length; i++) {
				if (m_aResponseTimers[i] < m_iFastestResponseTime) {
					m_iFastestResponseTime = m_aResponseTimers[i];
				}
			}
		}
	}
	
	public void setReactionTimer(int step, long reactionTime) {
		m_aReactionTimers[step] = reactionTime;
		
		if (step == NUMBER_OF_TRIALS - 1) {
			m_iFastestReactionTime = m_aReactionTimers[0];
			for (int i = 1; i < m_aReactionTimers.length; i++) {
				if (m_aReactionTimers[i] < m_iFastestReactionTime) {
					m_iFastestReactionTime = m_aReactionTimers[i];
				}
			}
		}
	}
	
	public void setPoints(int step, float points) {
		m_aPoints[step] = points;
	}

	public int getElementAt(int index) {
		return m_aEntries[index];
	}

	public int getSize() {
		return m_aEntries.length;
	}

	public String getExportString() {
		String exportString = "Response Timings (ms): ";

		for (int i = 0; i < m_aResponseTimers.length; i++) {
			exportString += m_aResponseTimers[i] + ", ";
		}
		
		exportString += "\r\nReaction Timings (ms): ";
		
		for (int i = 0; i < m_aReactionTimers.length; i++) {
			exportString += m_aReactionTimers[i] + ", ";
		}
		
		exportString += "\r\nEntries: ";

		for (int i = 0; i < m_aEntries.length; i++) {
			exportString += m_aEntries[i] + ", ";
		}

		exportString += "\r\nFastest Response Time (ms): " + m_iFastestResponseTime + "ms";
		exportString += "\r\nFastest Reaction Time (ms): " + m_iFastestReactionTime + "ms \r\n";

		return exportString;
	}
}