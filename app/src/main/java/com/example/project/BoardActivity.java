package com.example.project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BoardActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;

    RecyclerView recyclerView;
    CustomAdapter adapter;
    ArrayList<BoardData> boardList;
    Button writeBtn;

    TextView emptyView;

    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getInstance().getReference();

        mContext = this;
        emptyView = findViewById(R.id.empty_view);
        writeBtn = findViewById(R.id.write_btn);
        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BoardWriteDialog dialog = new BoardWriteDialog(mContext);
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        String resultCode = ((BoardWriteDialog) dialogInterface).resultCode;
                        if(resultCode.equals("ok")){
                            /**
                             *   todo
                             *   username 에 로그인한 유저이름을 넣으면 됨
                             */
                            FirebaseUser user = mAuth.getCurrentUser();

                            mReference.child("UserAccount").child(user.getUid()).child("name").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String value = snapshot.getValue(String.class);
                                    String username = value;
                                    String title =  ((BoardWriteDialog) dialogInterface).titleText;
                                    String board =  ((BoardWriteDialog) dialogInterface).boardText;
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                                    String date  = sdf.format(new Date());
                                    BoardData boardData = new BoardData(username,title ,date , board);
                                    db.collection("boardlist").add(boardData);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            getList();
                        }
                    }
                });
                dialog.show();
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            }
        });
        adapter = new CustomAdapter();
        adapter.setListener(new MyEventListener() {
            @Override
            public void onCliclkEvent(int id) {
                BoardDetailDialog dialog = new BoardDetailDialog(mContext , boardList.get(id).title, boardList.get(id).date,boardList.get(id).board,boardList.get(id).name);
                dialog.show();
            }
        });
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        db = FirebaseFirestore.getInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getList();
    }

    public void getList(){

        db.collection("boardlist").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                boardList = new ArrayList<>();
                if(queryDocumentSnapshots.size() != 0){
                    emptyView.setVisibility(View.GONE);
                    for(int i=0 ; i < queryDocumentSnapshots.size(); i++){
                        String name = queryDocumentSnapshots.getDocuments().get(i).get("name").toString();
                        String title = queryDocumentSnapshots.getDocuments().get(i).get("title").toString();
                        String date = queryDocumentSnapshots.getDocuments().get(i).get("date").toString();
                        String board = queryDocumentSnapshots.getDocuments().get(i).get("board").toString();
                        BoardData boardData = new BoardData(name,title,date,board);
                        boardList.add(boardData);
                    }

                    adapter.setList(boardList);
                    adapter.notifyDataSetChanged();
                } else {
                    emptyView.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
        private ArrayList<BoardData> mList;
        public MyEventListener listener;
        public class CustomViewHolder extends RecyclerView.ViewHolder {
            protected TextView titleView, nameView , dateView;
            public CustomViewHolder(View view) {
                super(view);
                this.titleView = (TextView) view.findViewById(R.id.list_title);
                this.nameView = (TextView) view.findViewById(R.id.list_user_name);
                this.dateView = (TextView) view.findViewById(R.id.list_date);
            }
        }

        public void setList(ArrayList<BoardData> list) {
            this.mList = list;
        }
        public void setListener(MyEventListener listener) {
            this.listener = listener;
        }
        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.list_board, viewGroup, false);
            CustomViewHolder viewHolder = new CustomViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull CustomViewHolder viewholder, @SuppressLint("RecyclerView") int position) {
            viewholder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCliclkEvent(position);
                }
            });
            viewholder.titleView.setText(mList.get(position).getTitle());
            viewholder.nameView.setText(mList.get(position).getName());
            viewholder.dateView.setText(mList.get(position).getDate());
        }
        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return (null != mList ? mList.size() : 0);
        }
    }

    public interface MyEventListener {
        void onCliclkEvent(int id);
    }

}