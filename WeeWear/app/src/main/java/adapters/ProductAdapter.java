//package adapters;
//// ProductAdapter.java
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import com.bumptech.glide.Glide;
//
//import java.util.List;
//import models.Product;
//
//public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
//    private Context context;
//    private List<Product> productList;
//
//    public ProductAdapter(Context context, List<Product> productList) {
//        this.context = context;
//        this.productList = productList;
//    }
//
//    @NonNull
//    @Override
//    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
//        return new ProductViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
//        Product product = productList.get(position);
//        holder.nameTextView.setText(product.getName());
//        holder.priceTextView.setText("$" + product.getPrice());
//        Glide.with(context).load(product.getImageUrl()).into(holder.productImageView);
//    }
//
//    @Override
//    public int getItemCount() {
//        return productList.size();
//    }
//
//    public static class ProductViewHolder extends RecyclerView.ViewHolder {
//        TextView nameTextView, priceTextView;
//        ImageView productImageView;
//
//        public ProductViewHolder(@NonNull View itemView) {
//            super(itemView);
//            nameTextView = itemView.findViewById(R.id.productNameTextView);
//            priceTextView = itemView.findViewById(R.id.productPriceTextView);
//            productImageView = itemView.findViewById(R.id.productImageView);
//        }
//    }
//}