import android.text.Editable;
import android.text.TextWatcher;

public class RemoveWordTextWatcher implements TextWatcher {
    private static final String TAG = RemoveWordTextWatcher.class.getSimpleName();
    boolean isBackspace = false;
    boolean isDelete = false;
    boolean isLastCharSpace = false;
    CharSequence beforeText = "";
    int beforeLength = 0;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        beforeLength = s.length();
        beforeText = s;
        if (beforeLength > 0)
            isLastCharSpace = Character.isWhitespace(s.toString().charAt(s.toString().length() - 1));
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        isBackspace = (s.length() < beforeLength);
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (isBackspace && !isDelete && !isLastCharSpace) {
            if (s.toString().contains(" ")) {
                String[] words = s.toString().split(" ");
                isDelete = true;
                s.delete(s.toString().length() - words[words.length - 1].length(), s.toString().length());
            } else {
                isDelete = true;
                s.delete(0, s.toString().length());
            }
        } else {
            isDelete = false;
        }

    }
}
