# Data Structure Performance

## Overview
A performance comparison of major data structures including AVL Trees, Splay Trees, and Hash Tables. The program benchmarks insertion, search, and deletion operations based on time and memory usage.

## Implemented Structures
- **AVL Tree:** Self-balancing binary search tree
- **Splay Tree:** Self-adjusting BST with recent-node splaying
- **Hash Table (Chaining):** Linked list-based collision handling
- **Hash Table (Quadratic Probing):** Open addressing with quadratic probing

## Features
- Inserts, searches, and deletes random integers
- Tests each structure with 1K, 10K, and 100K elements
- Measures operation time and memory usage
- Compares efficiency across structures

## Findings
- **Hash Table (Chaining)** had fastest insertions (O(1))
- **AVL Tree** had most efficient searches (O(log n))
- **Splay Tree** slower for large datasets due to O(n) splaying
- **Quadratic Probing** efficient for well-distributed hash tables

## Tools
- Java
- Custom timing and memory tracking utilities
