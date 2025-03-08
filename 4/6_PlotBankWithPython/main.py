import polars as ps
import matplotlib.pyplot as plt

# Load the data
def load_data(file_path: str):
    # Read the file into a pandas DataFrame
    data = ps.read_csv(file_path, separator=";", has_header=False, new_columns=["timestamp", "curr_bank_money", "curr_wallet_money"], try_parse_dates=True)
    return data

# Plot the data
def plot_quantity_changes(data):
    # Plot the quantity over time
    plt.figure(figsize=(10, 6))
    plt.plot(data['timestamp'], data['curr_bank_money'], marker='o', linestyle='-', color='b', label="Bank account trend")
    plt.plot(data['timestamp'], data['curr_wallet_money'], marker='h', linestyle='--', color='g', label="Wallet trend")
    plt.legend()
    plt.title('Money Changes Over Time')
    plt.xlabel('Timestamp')
    plt.ylabel('Money')
    plt.xticks(rotation=45)
    plt.grid(True)
    plt.tight_layout()
    plt.show()


if __name__ == "__main__":
    path: str = input("Input the path to the csv file")
    if path == "":
        path = "data.csv"
    dat = load_data(file_path=path)
    plot_quantity_changes(data=dat)