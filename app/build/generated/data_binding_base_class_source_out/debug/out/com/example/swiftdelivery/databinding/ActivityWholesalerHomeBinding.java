// Generated by view binder compiler. Do not edit!
package com.example.swiftdelivery.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.swiftdelivery.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityWholesalerHomeBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button addNewProductsBtn;

  @NonNull
  public final Button logoutBtn;

  @NonNull
  public final Button pendingOrdersBtn;

  @NonNull
  public final TextView wholesalerName;

  private ActivityWholesalerHomeBinding(@NonNull RelativeLayout rootView,
      @NonNull Button addNewProductsBtn, @NonNull Button logoutBtn,
      @NonNull Button pendingOrdersBtn, @NonNull TextView wholesalerName) {
    this.rootView = rootView;
    this.addNewProductsBtn = addNewProductsBtn;
    this.logoutBtn = logoutBtn;
    this.pendingOrdersBtn = pendingOrdersBtn;
    this.wholesalerName = wholesalerName;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityWholesalerHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityWholesalerHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_wholesaler_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityWholesalerHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.add_new_products_btn;
      Button addNewProductsBtn = rootView.findViewById(id);
      if (addNewProductsBtn == null) {
        break missingId;
      }

      id = R.id.logout_btn;
      Button logoutBtn = rootView.findViewById(id);
      if (logoutBtn == null) {
        break missingId;
      }

      id = R.id.pending_orders_btn;
      Button pendingOrdersBtn = rootView.findViewById(id);
      if (pendingOrdersBtn == null) {
        break missingId;
      }

      id = R.id.wholesaler_name;
      TextView wholesalerName = rootView.findViewById(id);
      if (wholesalerName == null) {
        break missingId;
      }

      return new ActivityWholesalerHomeBinding((RelativeLayout) rootView, addNewProductsBtn,
          logoutBtn, pendingOrdersBtn, wholesalerName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
