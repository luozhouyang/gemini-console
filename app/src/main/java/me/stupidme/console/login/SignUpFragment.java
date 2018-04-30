package me.stupidme.console.login;

import android.content.Context;

import me.stupidme.console.R;
import me.stupidme.console.ServerConfigure;
import me.stupidme.console.account.UserInfoItem;
import me.stupidme.stupidhttp.PostRequest;
import me.stupidme.stupidhttp.Request;
import me.stupidme.stupidhttp.RequestCallback;
import me.stupidme.stupidhttp.StupidHttp;

public class SignUpFragment extends BaseFragment {

    private OnSignUpListener mListener;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public int getTextResId() {
        return R.string.action_sign_up;
    }

    @Override
    public void doAction() {
        String name = mUserNameView.getText().toString();
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        Request request = new PostRequest(ServerConfigure.getSignUpURL(),
                new UserInfoItem(name, email, password));
        RequestCallback callback = new RequestCallback() {
            @Override
            public void onException(Exception e) {
                showProgress(false);
                if (mListener != null) {
                    mListener.onSignUpFailed(e);
                }
            }

            @Override
            public void onSuccess(String response) {
                showProgress(false);
                if (mListener != null) {
                    mListener.onSignUpSuccess();
                }
            }
        };
        StupidHttp.getInstance().go(request, callback);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSignUpListener) {
            mListener = (OnSignUpListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLoginListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnSignUpListener {
        void onSignUpSuccess();

        void onSignUpFailed(Exception e);
    }
}
