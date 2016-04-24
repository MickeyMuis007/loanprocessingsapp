package Repository.loanprocess.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import Domain.Branch;
import Repository.loanprocess.BranchRepository;
import config.databases.DBConstants;
import services.valueobjects.Address;

/**
 * Created by Riaan on 4/23/2016.
 */
public class BranchRepositoryImpl extends SQLiteOpenHelper implements BranchRepository {
    public static final String TABLE_NAME = "BRANCH";
    private SQLiteDatabase db;
    private Address address;

    public static final String COLUMN_BRANCHID = "BRANCH_ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_STREETNO = "STREET_NO";
    public static final String COLUMN_STREETNAME = "STREET_NAME";
    public static final String COLUMN_SUBURB = "SUBURB";
    public static final String COLUMN_CITY = "CITY";
    public static final String COLUMN_POSTALCODE = "POSTAL_CODE";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_BRANCHID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT  NOT NULL , "
            + COLUMN_STREETNO + " INTEGER , "
            + COLUMN_STREETNAME + " TEXT NOT NULL, "
            + COLUMN_SUBURB + " TEXT , "
            + COLUMN_CITY + " TEXT  NOT NULL , "
            + COLUMN_POSTALCODE + " INTEGER NOT NULL );";

    public BranchRepositoryImpl(Context context){
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
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
    public Branch findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_BRANCHID,
                        COLUMN_NAME,
                        COLUMN_STREETNO,
                        COLUMN_STREETNAME,
                        COLUMN_SUBURB,
                        COLUMN_CITY,
                        COLUMN_POSTALCODE},
                COLUMN_BRANCHID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if(cursor.moveToFirst()){
            assignAddress(cursor);
            final Branch branch = new Branch
                    .Builder()
                    .branchNo(cursor.getLong(cursor.getColumnIndex(COLUMN_BRANCHID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .address(address)
                    .build();
            return branch;
        }
        return null;
    }

    @Override
    public Branch save(Branch entity) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_BRANCHID, entity.getBranchNo());
        contentValues.put(COLUMN_NAME, entity.getName());
        contentValues.put(COLUMN_STREETNO, entity.getAddress().getNo());
        contentValues.put(COLUMN_STREETNAME, entity.getAddress().getStreet());
        contentValues.put(COLUMN_SUBURB,entity.getAddress().getSuburb());
        contentValues.put(COLUMN_CITY, entity.getAddress().getCity());
        contentValues.put(COLUMN_POSTALCODE, entity.getAddress().getPostalCode());
        Long id = db.insertOrThrow(TABLE_NAME,null,contentValues);
        Branch insertEntity = new Branch
                .Builder()
                .copy(entity)
                .branchNo(new Long(id))
                .build();
        return insertEntity;
    }

    @Override
    public Branch update(Branch entity) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_BRANCHID, entity.getBranchNo());
        contentValues.put(COLUMN_NAME, entity.getName());
        contentValues.put(COLUMN_STREETNO, entity.getAddress().getNo());
        contentValues.put(COLUMN_STREETNAME, entity.getAddress().getStreet());
        contentValues.put(COLUMN_SUBURB,entity.getAddress().getSuburb());
        contentValues.put(COLUMN_CITY, entity.getAddress().getCity());
        contentValues.put(COLUMN_POSTALCODE, entity.getAddress().getPostalCode());
        db.update(TABLE_NAME,
                contentValues,
                COLUMN_BRANCHID + " =? ",
                new String[]{String.valueOf(entity.getBranchNo())});
        return entity;
    }

    @Override
    public Branch delete(Branch entity) {
        open();
        db.delete(TABLE_NAME,
                COLUMN_BRANCHID + " =? ",
                new String[]{String.valueOf(entity.getBranchNo())});
        return entity;
    }

    @Override
    public Set<Branch> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Branch> branches = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                assignAddress(cursor);
                final Branch branch = new Branch
                        .Builder()
                        .branchNo(cursor.getLong(cursor.getColumnIndex(COLUMN_BRANCHID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .address(address)
                        .build();
                branches.add(branch);
            }while(cursor.moveToNext());
        }
        return branches;
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
