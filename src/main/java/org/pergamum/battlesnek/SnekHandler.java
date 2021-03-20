package org.pergamum.battlesnek;

import org.pergamum.battlesnek.api.MoveResponse;
import org.pergamum.battlesnek.api.Request;
import org.pergamum.battlesnek.api.SnekInitResponse;

public interface SnekHandler {

	public SnekInitResponse initialize();
	public MoveResponse move(Request request);
	public void start(Request request);
	public void end(Request request);
}
