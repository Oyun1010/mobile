package com.example.plan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import data.DatabaseHandler;
import model.Plan;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.TodoViewHolder> {
    private ArrayList<Plan> plansItem;
    DatabaseHandler DB;
    Context context;
    MainActivity mainActivity = new MainActivity();

    public TodoListAdapter(Context context, ArrayList<Plan> plansItem) {
        this.plansItem = plansItem;
        this.context = context;
        this.DB = new DatabaseHandler(context);

    }

    public void displayToast(String message) {
        Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View mItemView = layoutInflater.inflate(R.layout.todo_item, parent, false);
        return new TodoViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        holder.todoTextView.setText(plansItem.get(position).getToDo());
        holder.dateTimeView.setText(plansItem.get(position).getDateTime());

        if (plansItem.get(position).getState() == 0) {
            holder.checkBox.setChecked(false);
        } else {
            holder.checkBox.setChecked(true);
        }
    }

    @Override
    public int getItemCount() {
        return plansItem.size();
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {
        public final CheckBox checkBox;
        public final TextView todoTextView;
        public final TextView dateTimeView;
        public final ImageView deleteView;
        public final ImageView editView;
        public final TodoListAdapter mAdapter;

        public TodoViewHolder(@NonNull View itemView, TodoListAdapter adapter) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.check);
            todoTextView = itemView.findViewById(R.id.to_do);
            dateTimeView = itemView.findViewById(R.id.date_time);
            editView = itemView.findViewById(R.id.edit_btn);
            deleteView = itemView.findViewById(R.id.delete_btn);
            Log.d("POS : ", String.valueOf(getLayoutPosition()));
            this.mAdapter = adapter;
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(checkBox.isChecked()) {

                        plansItem.get(getLayoutPosition()).setState(1);

                        Boolean check = DB.updateData(plansItem.get(getLayoutPosition()));
                        if (check) {
                            notifyDataSetChanged();
                            displayToast("Aмжилттай хадгаллаа");
                        } else {
                            displayToast("Алдаа гарлаа дахин оролдоно уу.");
                        }

                    }
                    else {
                        plansItem.get(getLayoutPosition()).setState(0);
                        Boolean check = DB.updateData(plansItem.get(getLayoutPosition()));
                        if (check) {
                            notifyDataSetChanged();
                            displayToast("Aмжилттай хадгаллаа");
                        } else {
                            displayToast("Алдаа гарлаа дахин оролдоно уу.");
                        }
                    }

                }
            });

            editView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    LayoutInflater layoutInflater = LayoutInflater.from(context);
                    View alertView = layoutInflater.inflate(R.layout.dialog, null);
                    EditText editText = alertView.findViewById(R.id.edit_todo);
                    EditText editText1 = alertView.findViewById(R.id.edit_dateTime);

                    editText.setText(plansItem.get(getLayoutPosition()).getToDo());
                    editText1.setText(plansItem.get(getLayoutPosition()).getDateTime());



                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setTitle("Төлөвлөгөөг өөрчлөх");
                    alertBuilder.setView(alertView);

                    alertBuilder.setPositiveButton("Хадгалах", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Log.d("Plans Item", plansItem.get(getLayoutPosition()).getToDo());
                            Log.d("ID : ", String.valueOf(plansItem.get(getLayoutPosition()).getItemID()));

                            plansItem.get(getLayoutPosition()).setToDo(editText.getText().toString());
                            plansItem.get(getLayoutPosition()).setDateTime(editText1.getText().toString());

                            Boolean check = DB.updateData(plansItem.get(getLayoutPosition()));
                            if (check) {
                                notifyDataSetChanged();
                                displayToast("Aмжилттай хадгаллаа");
                            } else {
                                displayToast("Алдаа гарлаа дахин оролдоно уу.");
                            }
                        }
                    });
                    alertBuilder.setNegativeButton("Цуцлах", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            displayToast("Цуцлалаа");

                        }
                    });
                    alertBuilder.show();
                }
            });


            deleteView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Len ", String.valueOf(plansItem.size()));


                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setTitle("Баталгаажуулах");
                    alertBuilder.setMessage("Төлөвлөгөөг устах уу ?");
                    alertBuilder.setPositiveButton("Тийм", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Boolean check = DB.deleteData(plansItem.get(getLayoutPosition()));
                            if (check) {
                                plansItem.remove(getLayoutPosition());
                                notifyItemRemoved(getLayoutPosition());
                                Log.d("Len  1 ", String.valueOf(plansItem.size()));
                                displayToast("Төлөвлөгөө амжилттай устлаа");
                            } else {
                                displayToast("Алдаа гарлаа дахин оролдоно уу.");
                            }
                        }
                    });
                    alertBuilder.setNegativeButton("Үгүй", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            displayToast("Цуцлалаа");

                        }
                    });
                    alertBuilder.show();
                }
            });
        }
    }
}
