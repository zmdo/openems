package com.schenchi.ems.platform.editor;

import com.schenchi.ems.platform.service.DataService;

public abstract class EditorBase implements IEditor {

	private EditorState state = EditorState.READY;
	
	@Override
	public EditorState getState() {
		return state;
	}

	@Override
	public void onCreate(DataService service) {
		state = EditorState.CREATE;
	}

	@Override
	public void onStart(DataService service) {
		state = EditorState.START;
	}

	@Override
	public void onResume(DataService service) {
		state = EditorState.RESUME;
	}

	@Override
	public void onPause(DataService service) {
		state = EditorState.PAUSE;
	}

	@Override
	public void onStop(DataService service) {
		state = EditorState.STOP;
	}

	@Override
	public void onDestroy(DataService service) {
		state = EditorState.DESTORY;
	}

	@Override
	public void onRestart(DataService service) {
		state = EditorState.RESTART;
	}
}
