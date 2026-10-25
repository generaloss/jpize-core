package jpize.android.context.input;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.widget.EditText;
import android.widget.FrameLayout;

import generaloss.resourceflow.Disposable;
import jpize.context.Jpize;
import jpize.context.input.Action;
import jpize.context.input.Key;

public final class AndroidTextInput implements Disposable {

    private final Activity activity;
    private final InputEditText editText;
    private final InputMethodManager inputMethodManager;

    private boolean disposed;

    public AndroidTextInput(Activity activity, FrameLayout root) {
        this.activity = activity;

        this.inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

        this.editText = new InputEditText(activity);

        this.editText.setSingleLine(true);
        this.editText.setInputType(InputType.TYPE_CLASS_TEXT);
        this.editText.setImeOptions(EditorInfo.IME_ACTION_DONE);

        this.editText.setTextColor(Color.TRANSPARENT);
        this.editText.setBackgroundColor(Color.TRANSPARENT);
        this.editText.setCursorVisible(false);
        this.editText.setAlpha(0.01F);

        this.editText.setFocusable(true);
        this.editText.setFocusableInTouchMode(true);

        final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(1, 1, Gravity.TOP | Gravity.START);
        root.addView(editText, params);
    }

    private final class InputEditText extends EditText {

        public InputEditText(Context context) {
            super(context);
        }

        @Override
        public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
            final InputConnection target = super.onCreateInputConnection(outAttrs);
            if(target == null)
                return null;

            outAttrs.inputType = InputType.TYPE_CLASS_TEXT;
            outAttrs.imeOptions = EditorInfo.IME_ACTION_DONE;

            return new InputConnectionWrapper(target, true) {

                @Override
                public boolean commitText(CharSequence text, int newCursorPosition) {
                    if(text != null)
                        emitCharacters(text);

                    return true;
                }

                @Override
                public boolean setComposingText(CharSequence text, int newCursorPosition) {
                    return true;
                }

                @Override
                public boolean setComposingRegion(int start, int end) {
                    return true;
                }

                @Override
                public boolean finishComposingText() {
                    return true;
                }

                @Override
                public boolean deleteSurroundingText(int beforeLength, int afterLength) {
                    if(beforeLength > 0) {
                        emitBackspace(Action.DOWN);
                        emitBackspace(Action.UP);
                    }

                    return true;
                }

                @Override
                public boolean deleteSurroundingTextInCodePoints(int beforeLength, int afterLength) {
                    if(beforeLength > 0) {
                        emitBackspace(Action.DOWN);
                        emitBackspace(Action.UP);
                    }

                    return true;
                }

                @Override
                public boolean sendKeyEvent(KeyEvent event) {
                    final int keyCode = event.getKeyCode();
                    final int action = event.getAction();

                    if(keyCode == KeyEvent.KEYCODE_DEL) {
                        if(action == KeyEvent.ACTION_DOWN) {
                            emitKey(
                                Key.BACKSPACE,
                                event.getScanCode(),
                                Action.DOWN,
                                event.getModifiers()
                            );
                        } else if(action == KeyEvent.ACTION_UP) {
                            emitKey(
                                Key.BACKSPACE,
                                event.getScanCode(),
                                Action.UP,
                                event.getModifiers()
                            );
                        }

                        return true;
                    }

                    if(keyCode == KeyEvent.KEYCODE_ENTER) {
                        if(action == KeyEvent.ACTION_DOWN) {
                            emitKey(
                                Key.ENTER,
                                event.getScanCode(),
                                Action.DOWN,
                                event.getModifiers()
                            );
                        } else if(action == KeyEvent.ACTION_UP) {
                            emitKey(
                                Key.ENTER,
                                event.getScanCode(),
                                Action.UP,
                                event.getModifiers()
                            );
                        }

                        return true;
                    }

                    return true;
                }

                @Override
                public boolean performEditorAction(int actionCode) {
                    emitKey(Key.ENTER, 0, Action.DOWN, 0);
                    emitKey(Key.ENTER, 0, Action.UP, 0);
                    return true;
                }

                @Override
                public boolean setSelection(int start, int end) {
                    return true;
                }

                @Override
                public boolean commitCompletion(CompletionInfo text) {
                    return true;
                }

                @Override
                public boolean commitCorrection(CorrectionInfo correctionInfo) {
                    return true;
                }

                @Override
                public boolean performContextMenuAction(int id) {
                    return true;
                }

                @Override
                public boolean beginBatchEdit() {
                    return true;
                }

                @Override
                public boolean endBatchEdit() {
                    return true;
                }

                @Override
                public boolean clearMetaKeyStates(int states) {
                    return true;
                }
            };
        }
    }

    private void emitCharacters(CharSequence text) {
        for(int i = 0; i < text.length(); ) {
            final int codePoint = Character.codePointAt(text, i);

            Jpize.callbacks.invokeChar(codePoint);

            i += Character.charCount(codePoint);
        }
    }

    private void emitBackspace(Action action) {
        emitKey(Key.BACKSPACE, 0, action, 0);
    }

    private void emitKey(Key key, int scanCode, Action action, int modifiers) {
        Jpize.callbacks.invokeKey(
            key,
            scanCode,
            action,
            new AndroidMods(modifiers)
        );
    }

    public void show() {
        activity.runOnUiThread(() -> {
            if(disposed)
                return;

            editText.requestFocus();

            editText.post(() -> {
                if(!disposed)
                    inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            });
        });
    }

    public void hide() {
        activity.runOnUiThread(() -> {
            editText.clearFocus();
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        });
    }

    public boolean visible() {
        return editText.isFocused();
    }

    @Override
    public void dispose() {
        activity.runOnUiThread(() -> {
            if(disposed)
                return;
            disposed = true;

            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);

            if(editText.getParent() instanceof FrameLayout)
                ((FrameLayout) editText.getParent())
                    .removeView(editText);
        });
    }

}