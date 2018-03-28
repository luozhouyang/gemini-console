package me.stupidme.console.login;

/**
 * Created by allen on 18-3-28.
 */
class LoginPresenterImpl implements LoginPresenter {

    private LoginView mView;
    private LoginModel mModel;

    LoginPresenterImpl(LoginView view, LoginModel model) {
        mView = view;
        mModel = model;
    }

    @Override
    public void login(String username, String password) {
        boolean success = mModel.login(username, password);
        if (success) {
            if (mView != null)
                mView.loginSuccess();
        } else {
            if (mView != null)
                mView.loginFailed();
        }
    }
}
