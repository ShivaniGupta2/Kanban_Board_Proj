import { Column } from "./Column.model";
import { Members } from "./Members.model";

export class Project {
    projectname: string | undefined;
    author: string | undefined;
    member: Array<Members> | undefined;
    columnList: Array<Column> | undefined;
}