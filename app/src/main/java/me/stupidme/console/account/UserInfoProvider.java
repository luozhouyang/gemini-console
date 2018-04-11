package me.stupidme.console.account;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by allen on 18-4-11.
 */
public class UserInfoProvider extends ContentProvider {

    private static final String TAG = "UserInfoProvider";
    public static final String AUTHORITY = "me.stupidme.console.USERINFO_PROVIDER";

    public static final Uri USER_INFO_URI = Uri.parse("content://" + AUTHORITY + "/userinfo");

    public static final int USER_INFO_URI_CODE = 0x01;


    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, "userinfo", USER_INFO_URI_CODE);
    }

    private SQLiteDatabase mDb;
    private Context mContext;

    @Override
    public boolean onCreate() {
        mDb = new UserInfoHelper(getContext()).getWritableDatabase();
        mContext = getContext();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        if (USER_INFO_URI_CODE != MATCHER.match(uri)) {
            throw new IllegalStateException("Cursor is null.");
        }
        return mDb.query(UserInfoHelper.TABLE_NAME, projection, selection, selectionArgs,
                null, null, sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (USER_INFO_URI_CODE == MATCHER.match(uri)) {
            mDb.insert(UserInfoHelper.TABLE_NAME, null, values);
            mContext.getContentResolver().notifyChange(uri, null);
        }
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        if (USER_INFO_URI_CODE == MATCHER.match(uri)) {
            count = mDb.delete(UserInfoHelper.TABLE_NAME, selection, selectionArgs);
        }
        if (count > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int row = 0;
        if (USER_INFO_URI_CODE == MATCHER.match(uri)) {
            row = mDb.update(UserInfoHelper.TABLE_NAME, values, selection, selectionArgs);
        }
        if (row > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }
        return row;
    }
}
