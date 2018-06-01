package ricm3.game.mvc;

public class Overhead {
	private static final long NOPS = 1000000L;

	  private long m_count = 0;
	  private long m_sum = 0;

	  void inc() {
	    m_count+=NOPS;
	    System.out.println("Overhead at " + m_count);
	  }
	  
	  void dec() {
	    m_count = m_count - NOPS;
	    if (m_count<0)
	      m_count=0;
	    System.out.println("Overhead at " + m_count);
	  }
	  
	  long op(long i) {
	    for (int o=0;o<5;o++)
	      new String("Waster");
	    return i + i * i;
	  }

	  /*
	   * *** WARNING *** WARNING *** WARNING *** WARNING  
	   * long callbacks will kill your frame-per-second performance
	   * the game will get sluggish...
	   * avoid as much as possible.
	   */
	  long overhead() {
	    for (long i = 0; i < m_count; i++)
	      m_sum += op(i);
	    return m_sum;
	  }

}
