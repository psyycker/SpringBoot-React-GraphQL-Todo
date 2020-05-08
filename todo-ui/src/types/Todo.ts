export interface TodoObject {
  id: string;
  name: string;
  description: string;
  done: boolean
  category: CategoryItem | null
}

export interface CategoryItem {
  id: string;
  name: string
}
