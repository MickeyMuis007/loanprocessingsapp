package Repository.loanprocess.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import Domain.Customer;
import Domain.Loan;
import Domain.User;
import Repository.loanprocess.LoanRepository;
import config.databases.DBConstants;

/**
 * Created by Riaan on 4/23/2016.
 */
public class LoanRepositoryImpl extends SQLiteOpenHelper implements LoanRepository {
    public static final String TABLE_NAME = "LOAN";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TYPE = "TYPE";
    public static final String COLUMN_INTEREST = "INTEREST";
    public static final String COLUMN_LOANAMOUNT = "LOAN_AMOUNT";
    public static final String COLUMN_NUMBEROFPAYMENTS = "NUMBER_OF_PAYMENTS";
    public static final String COLUMN_APPROVAL = "APPROVAL";
    public static final String COLUMN_CUSTOMERID = "CUSTOMER_ID";
    public static final String COLUMN_USERID = "USER_ID";

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TYPE +  " TEXT  NOT NULL , "
            + COLUMN_INTEREST + " DOUBLE NOT NULL, "
            + COLUMN_LOANAMOUNT + " DOUBLE NOT NULL, "
            + COLUMN_NUMBEROFPAYMENTS + " INTEGER NOT NULL, "
            + COLUMN_APPROVAL + " BOOLEAN NOT NULL DEFAULT 0"
            + COLUMN_CUSTOMERID + " INTEGER NOT NULL, "
            + COLUMN_USERID + " INTEGER NOT NULL );";

    public LoanRepositoryImpl(Context context){
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    private Customer assignCustomer(Cursor cursor){
        return new Customer
                .Builder()
                .customerId(cursor.getLong(cursor.getColumnIndex(COLUMN_CUSTOMERID)))
                .build();
    }
    private User assignUser(Cursor cursor){
        return new User
                .Builder()
                .userNo(cursor.getLong(cursor.getColumnIndex(COLUMN_USERID)))
                .build();
    }

    @Override
    public Loan findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_TYPE,
                        COLUMN_INTEREST,
                        COLUMN_LOANAMOUNT,
                        COLUMN_NUMBEROFPAYMENTS,
                        COLUMN_APPROVAL,
                        COLUMN_CUSTOMERID,
                        COLUMN_USERID},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );
        if(cursor.moveToFirst()){
            final Loan loan = new Loan
                    .Builder()
                    .loanReferenceNo(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .type(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)))
                    .loanAmount(cursor.getDouble(cursor.getColumnIndex(COLUMN_LOANAMOUNT)))
                    .numberOfPayments("Years", cursor.getInt(cursor.getColumnIndex(COLUMN_NUMBEROFPAYMENTS)))
                    .interest(cursor.getDouble(cursor.getColumnIndex(COLUMN_INTEREST)))
                    .approved(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_APPROVAL))))
                    .customer(assignCustomer(cursor))
                    .user(assignUser(cursor))
                    .build();
            return loan;
        }
        return null;
    }

    @Override
    public Loan save(Loan entity) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, entity.getLoanReferenceNo());
        contentValues.put(COLUMN_TYPE, entity.getType());
        contentValues.put(COLUMN_INTEREST, entity.getInterest());
        contentValues.put(COLUMN_LOANAMOUNT, entity.getLoanAmount());
        contentValues.put(COLUMN_NUMBEROFPAYMENTS, entity.getNumberOfPayments());
        contentValues.put(COLUMN_APPROVAL, entity.isApproved());
        contentValues.put(COLUMN_CUSTOMERID, entity.getCustomer().getCustomerId());
        contentValues.put(COLUMN_USERID, entity.getUser().getUserNo());
        Long id = db.insertOrThrow(TABLE_NAME, null, contentValues);
        Loan insertEntity = new Loan
                .Builder()
                .copy(entity)
                .loanReferenceNo(id)
                .build();
        return insertEntity;
    }

    @Override
    public Loan update(Loan entity) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, entity.getLoanReferenceNo());
        contentValues.put(COLUMN_TYPE, entity.getType());
        contentValues.put(COLUMN_INTEREST, entity.getInterest());
        contentValues.put(COLUMN_LOANAMOUNT, entity.getLoanAmount());
        contentValues.put(COLUMN_NUMBEROFPAYMENTS, entity.getNumberOfPayments());
        contentValues.put(COLUMN_APPROVAL, entity.isApproved());
        contentValues.put(COLUMN_CUSTOMERID, entity.getCustomer().getCustomerId());
        contentValues.put(COLUMN_USERID, entity.getUser().getUserNo());

        db.update(
                TABLE_NAME,
                contentValues,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getLoanReferenceNo())}
        );
        return entity;
    }

    @Override
    public Loan delete(Loan entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getLoanReferenceNo())}
        );
        return entity;
    }

    @Override
    public Set<Loan> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Loan> loans = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                final Loan loan = new Loan
                        .Builder()
                        .loanReferenceNo(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .type(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)))
                        .loanAmount(cursor.getDouble(cursor.getColumnIndex(COLUMN_LOANAMOUNT)))
                        .numberOfPayments("Years", cursor.getInt(cursor.getColumnIndex(COLUMN_NUMBEROFPAYMENTS)))
                        .interest(cursor.getDouble(cursor.getColumnIndex(COLUMN_INTEREST)))
                        .approved(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_APPROVAL))))
                        .customer(assignCustomer(cursor))
                        .user(assignUser(cursor))
                        .build();
                loans.add(loan);
            }while(cursor.moveToNext());
        }
        return loans;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
