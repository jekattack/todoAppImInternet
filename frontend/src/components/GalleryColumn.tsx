import './GalleryColumn.css';
import GalleryItem from "./GalleryItem";
import Todo from "../service/model";

interface GalleryColumnProps{
    todos: Todo[];
    onTaskChange: () => void;
}

export default function GalleryColumn(props: GalleryColumnProps) {

    const components = props.todos.map((t :Todo) => <GalleryItem todo={t} onTaskChange={props.onTaskChange} />);

    return (
        <div>
            {components}
        </div>
    );
}