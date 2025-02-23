#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

const int MAX_BITS = 30; // 2^30 is greater than 10^9

struct TrieNode {
    TrieNode* children[2];
    int index; // To store the index of the prefix_xor

    TrieNode() {
        children[0] = children[1] = nullptr;
        index = -1;
    }
};

void insert(TrieNode* root, int num, int index) {
    TrieNode* node = root;
    for (int i = MAX_BITS; i >= 0; --i) {
        int bit = (num >> i) & 1;
        if (!node->children[bit]) {
            node->children[bit] = new TrieNode();
        }
        node = node->children[bit];
    }
    node->index = index;
}

pair<int, int> query(TrieNode* root, int num) {
    TrieNode* node = root;
    int max_xor = 0;
    for (int i = MAX_BITS; i >= 0; --i) {
        int bit = (num >> i) & 1;
        int toggled_bit = 1 - bit;
        if (node->children[toggled_bit]) {
            max_xor |= (1 << i);
            node = node->children[toggled_bit];
        } else {
            node = node->children[bit];
        }
    }
    return {max_xor, node->index};
}

int main() {
    int n;
    cin >> n;
    vector<int> a(n);
    for (int i = 0; i < n; ++i) {
        cin >> a[i];
    }

    TrieNode* root = new TrieNode();
    insert(root, 0, -1); // Insert 0 with index -1

    int prefix_xor = 0;
    for (int r = 0; r < n; ++r) {
        prefix_xor ^= a[r];
        insert(root, prefix_xor, r);

        auto result = query(root, prefix_xor);
        int max_xor = result.first;
        int l = result.second + 1; // Since we inserted prefix_xor with index r

        cout << max_xor << " " << l << endl;
    }

    return 0;
}
