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
import Repository.loanprocess.CustomerRepository;
import config.databases.DBConstants;
import services.valueobjects.Address;
import services.valueobjects.FullName;

/**
 * Created by Riaan on 4/23/2016.
 */
public class CustomerRepositoryImpl extends SQLiteOpenHelper implements CustomerRepository {
    private static final String TABLE_NAME = "CUSTOMER";
    private SQLiteDatabase db;
    private Address address;
    private FullName fullName;

    private static final String COLUMN_CUSTOMERID = "CUSTOMER_ID";
    private static final String COLUMN_NAME = "NAME";
    private static final String COLUMN_MIDDLENAME = "MIDDLENAME";
    private static final String COLUMN_LASTNAME = "LASTNAME";
    private static final String COLUMN_TYPE = "TYPE";
    private static final String COLUMN_HOUSEAPPROVAL = "HOUSE_APPROVAL";
    private static final String COLUMN_CARAPPROVAL = "CAR_APPROVAL";
    private static final String COLUMN_INSTANTAPPROVAL = "INSTANT_APPROVAL";
    private static final String COLUMN_MONTHLYINCOME = "MONTHLY_INCOME";
    private static final String COLUMN_REQUESTAMOUNT = "REQUEST_AMOUNT";
    private static final String COLUMN_STREETNO = "STREET_NO";
    private static final String COLUMN_STREETNAME = "STREETNAME";
    private static final String COLUMN_SUBURB = "SUBURB";
    private static final String COLUMN_CITY = "CITY";
    private static final String COLUMN_POSTALCODE = "POSTAL_CODE";

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_CUSTOMERID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_MIDDLENAME + " TEXT, "
            + COLUMN_LASTNAME + " TEXT, "
            + COLUMN_TYPE + " TEXT NOT NULL, "
            + COLUMN_HOUSEAPPROVAL + " BOOLEAN NOT NULL DEFAULT 0, "
            + COLUMN_CARAPPROVAL + " BOOLEAN NOT NULL DEFAULT 0, "
            + COLUMN_INSTANTAPPROVAL + " BOOLEAN NOT NULL DEFAULT 0, "
            + COLUMN_MONTHLYINCOME + " DOUBLE NOT NULL, "
            + COLUMN_REQUESTAMOUNT + " DOUBLE NOT NULL, "
            + COLUMN_STREETNO + " INTEGER NOT NULL, "
            + COLUMN_STREETNAME + " TEXT NOT NULL, "
            + COLUMN_SUBURB + " TEXT, "
            + COLUMN_CITY + " TEXT NOT NULL, "
            + COLUMN_POSTALCODE + " INTEGER NOT NULL );";

    public CustomerRepositoryImpl(Context context){
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }
    public void open() throws SQLException{
        db = this.getWritableDatabase();
    }
    public void close(){
        this.close();
    }

    private void assignFullName(Cursor cursor){
        if(cursor.getString(cursor.getColumnIndex(COLUMN_MIDDLENAME)) != "" && cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)) != "") {
            fullName = new FullName
                    .Builder()
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .middleName(cursor.getString(cursor.getColumnIndex(COLUMN_MIDDLENAME)))
                    .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                    .build();
        }
        else if(cursor.getString(cursor.getColumnIndex(COLUMN_MIDDLENAME)) == "" && cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)) != ""){
            fullName = new FullName
                    .Builder()
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                    .build();
        }
        else if(cursor.getString(cursor.getColumnIndex(COLUMN_MIDDLENAME)) != "" && cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)) == ""){
            fullName = new FullName
                    .Builder()
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_MIDDLENAME)))
                    .build();
        }
        else{
            fullName = new FullName
                    .Builder()
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .build();
        }
    }

    private void assignAddress(Cursor cursor){
        if(cursor.getString(cursor.getColumnIndex(COLUMN_SUBURB)) != "") {
            address = new Address
                    .Builder()
                    .no(cursor.getInt(cursor.getColumnIndex(COLUMN_STREETNO)))
                    .street(cursor.getString(cursor.getColumnIndex(COLUMN_STREETNAME)))
                    .suburb(cursor.getString(cursor.getColumnIndex(COLUMN_SUBURB)))
                    .city(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                    .postalCode(cursor.getInt(cursor.getColumnIndex(COLUMN_POSTALCODE)))
                    .build();
        }
        else{
            address = new Address
                    .Builder()
                    .no(cursor.getInt(cursor.getColumnIndex(COLUMN_STREETNO)))
                    .street(cursor.getString(cursor.getColumnIndex(COLUMN_STREETNAME)))
                    .city(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                    .postalCode(cursor.getInt(cursor.getColumnIndex(COLUMN_POSTALCODE)))
                    .build();
        }
    }



    @Override
    public Customer findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_CUSTOMERID,
                        COLUMN_NAME,
                        COLUMN_MIDDLENAME,
                        COLUMN_LASTNAME,
                        COLUMN_TYPE,
                        COLUMN_HOUSEAPPROVAL,
                        COLUMN_CARAPPROVAL,
                        COLUMN_INSTANTAPPROVAL,
                        COLUMN_MONTHLYINCOME,
                        COLUMN_REQUESTAMOUNT,
                        COLUMN_STREETNO,
                        COLUMN_STREETNAME,
                        COLUMN_SUBURB,
                        COLUMN_CITY,
                        COLUMN_POSTALCODE},
                COLUMN_CUSTOMERID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if(cursor.moveToFirst()){
            assignAddress(cursor);
            assignFullName(cursor);
            final Customer customer = new Customer
                    .Builder()
                    .customerId(cursor.getLong(cursor.getColumnIndex(COLUMN_CUSTOMERID)))
                    .fullName(fullName)
                    .address(address)
                    .type(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)))
                    .carApproval(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_CARAPPROVAL))))
                    .houseApproval(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_HOUSEAPPROVAL))))
                    .instantApproval(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_INSTANTAPPROVAL))))
                    .monthlyIncome(cursor.getDouble(cursor.getColumnIndex(COLUMN_MONTHLYINCOME)))
                    .requestAmount(cursor.getDouble(cursor.getColumnIndex(COLUMN_REQUESTAMOUNT)))
                    .build();
            return customer;
        }
        return null;
    }

    @Override
    public Customer save(Customer entity) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CUSTOMERID, entity.getCustomerId());
        contentValues.put(COLUMN_NAME, entity.getFullName().getName());
        contentValues.put(COLUMN_MIDDLENAME, entity.getFullName().getMiddleName());
        contentValues.put(COLUMN_LASTNAME, entity.getFullName().getLastName());
        contentValues.put(COLUMN_TYPE, entity.getType());
        contentValues.put(COLUMN_HOUSEAPPROVAL, entity.isHouseApproval());
        contentValues.put(COLUMN_CARAPPROVAL, entity.isCarApproval());
        contentValues.put(COLUMN_INSTANTAPPROVAL, entity.isInstantApproval());
        contentValues.put(COLUMN_MONTHLYINCOME, entity.getMonthlyIncome());
        contentValues.put(COLUMN_REQUESTAMOUNT, entity.getRequestAmount());
        contentValues.put(COLUMN_STREETNO, entity.getAddress().getNo());
        contentValues.put(COLUMN_STREETNAME, entity.getAddress().getStreet());
        contentValues.put(COLUMN_SUBURB, entity.getAddress().getSuburb());
        contentValues.put(COLUMN_CITY, entity.getAddress().getCity());
        contentValues.put(COLUMN_POSTALCODE, entity.getAddress().getPostalCode());
        Long id = db.insertOrThrow(TABLE_NAME, null, contentValues);
        Customer insertEntity = new Customer
                .Builder()
                .copy(entity)
                .customerId(id)
                .build();
        return insertEntity;
    }

    @Override
    public Customer update(Customer entity) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CUSTOMERID, entity.getCustomerId());
        contentValues.put(COLUMN_NAME, entity.getFullName().getName());
        contentValues.put(COLUMN_MIDDLENAME, entity.getFullName().getMiddleName());
        contentValues.put(COLUMN_LASTNAME, entity.getFullName().getLastName());
        contentValues.put(COLUMN_TYPE, entity.getType());
        contentValues.put(COLUMN_HOUSEAPPROVAL, entity.isHouseApproval());
        contentValues.put(COLUMN_CARAPPROVAL, entity.isCarApproval());
        contentValues.put(COLUMN_INSTANTAPPROVAL, entity.isInstantApproval());
        contentValues.put(COLUMN_MONTHLYINCOME, entity.getMonthlyIncome());
        contentValues.put(COLUMN_REQUESTAMOUNT, entity.getRequestAmount());
        contentValues.put(COLUMN_STREETNO, entity.getAddress().getNo());
        contentValues.put(COLUMN_STREETNAME, entity.getAddress().getStreet());
        contentValues.put(COLUMN_SUBURB, entity.getAddress().getSuburb());
        contentValues.put(COLUMN_CITY, entity.getAddress().getCity());
        contentValues.put(COLUMN_POSTALCODE, entity.getAddress().getPostalCode());
        db.update(
                TABLE_NAME,
                contentValues,
                COLUMN_CUSTOMERID + " =? ",
                new String[]{String.valueOf(entity.getCustomerId())}
        );
        return entity;
    }

    @Override
    public Customer delete(Customer entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_CUSTOMERID + " =? ",
                new String[]{String.valueOf(entity.getCustomerId())}
        );
        return entity;
    }

    @Override
    public Set<Customer> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Customer> customers = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do {
                assignAddress(cursor);
                assignFullName(cursor);
                final Customer customer = new Customer
                        .Builder()
                        .customerId(cursor.getLong(cursor.getColumnIndex(COLUMN_CUSTOMERID)))
                        .fullName(fullName)
                        .address(address)
                        .type(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)))
                        .carApproval(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_CARAPPROVAL))))
                        .houseApproval(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_HOUSEAPPROVAL))))
                        .instantApproval(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_INSTANTAPPROVAL))))
                        .monthlyIncome(cursor.getDouble(cursor.getColumnIndex(COLUMN_MONTHLYINCOME)))
                        .requestAmount(cursor.getDouble(cursor.getColumnIndex(COLUMN_REQUESTAMOUNT)))
                        .build();
                customers.add(customer);
            }while(cursor.moveToNext());
        }
        return customers;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrade databse from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
