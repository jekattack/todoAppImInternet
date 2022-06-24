import axios from "axios";
import {fireEvent, render, screen, waitFor} from "@testing-library/react";
import Form from "./Form";

test('Input is sent', async () => {

    jest.spyOn(axios, 'post').mockImplementation((url: string, data: any) => {
        expect(url).toEqual('http://localhost:8080/api/kanban/');
        expect(data).toEqual({task: "Schuhe kaufen", description: "Beim Schuhmann"});
        return Promise.resolve({});
    })

    const onTaskCreation = jest.fn(() => {});

    render(<Form onTaskCreation={onTaskCreation} />);

    const taskInput = screen.getByTestId('task-input');
    fireEvent.change(taskInput, ({target: {value: "Schuhe kaufen"}}));

    const descriptionInput = screen.getByTestId('description-input');
    fireEvent.change(descriptionInput, ({target: {value: "Beim Schuhmann"}}));

    screen.getByTestId("send-button").click();

    await waitFor(() => {
        expect((screen.getByTestId("task-input") as HTMLInputElement).value).toEqual("");
        expect((screen.getByTestId("description-input") as HTMLInputElement).value).toEqual("");
        expect(onTaskCreation).toHaveBeenCalledTimes(1);
    })


})