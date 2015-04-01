package com.smartstream.taker;

import com.smartstream.common.Data;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.SpaceInterruptedException;
import org.openspaces.core.context.GigaSpaceContext;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A taker bean starts a scheduled task that takes Data objects from the space
 * (in an unprocessed state).
 * 
 * <p>
 * The space is injected into this bean using OpenSpaces support for @GigaSpaceContext
 * annotation.
 * 
 * <p>
 * The scheduling uses the java.util.concurrent Scheduled Executor Service. It
 * is started and stopped based on Spring lifecycle events.
 */
public class Taker implements InitializingBean, DisposableBean {

	Logger log = Logger.getLogger(this.getClass().getName());

	private ScheduledExecutorService executorService;

	private ScheduledFuture<?> sf;

	private long numberOfTypes = 10;

	private long defaultDelay = 5000;

	private TakerTask takerTask;

	@GigaSpaceContext
	private GigaSpace gigaSpace;

	/**
	 * Sets the number of types that will be used to set
	 * {@link org.openspaces.example.data.common.Data#setType(Long)}.
	 * 
	 * <p>
	 * The type is used as the routing index for partitioned space. This will
	 * affect the distribution of Data objects over a partitioned space.
	 */
	public void setNumberOfTypes(long numberOfTypes) {
		this.numberOfTypes = numberOfTypes;
	}

	public void setDefaultDelay(long defaultDelay) {
		this.defaultDelay = defaultDelay;
	}

	public void afterPropertiesSet() throws Exception {
		log.info("--- STARTING TAKER WITH CYCLE [" + defaultDelay + "]");
		executorService = Executors.newScheduledThreadPool(1);
		takerTask = new TakerTask();
		sf = executorService.scheduleAtFixedRate(takerTask, defaultDelay,
				defaultDelay, TimeUnit.MILLISECONDS);
	}

	public void destroy() throws Exception {
		sf.cancel(false);
		sf = null;
		executorService.shutdown();
	}

	public long getTakeCount() {
		return takerTask.getCounter();
	}

	public class TakerTask implements Runnable {

		private long counter = 1;

		public void run() {
			try {
				Data data = gigaSpace.take(new Data());
				try {
					File file = new File("C:\\tmp\\myfile.txt");
					file.createNewFile();
					PrintWriter out = new PrintWriter(new BufferedWriter(
							new FileWriter("C:\\tmp\\myfile.txt", true)));
					out.println(data.toString() + "\n");
					out.close();
				} catch (IOException e) {
					log.log(Level.INFO, "error while writing to given file", e);
				}
				log.info("--- Data taked from space " + data);
			} catch (SpaceInterruptedException e) {
				// ignore, we are being shutdown
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public long getCounter() {
			return counter;
		}
	}

}
