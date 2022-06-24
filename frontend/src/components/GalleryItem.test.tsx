import {render, screen} from "@testing-library/react";
import GalleryItem from "./GalleryItem";
import axios from "axios";
import {MemoryRouter, Router} from "react-router-dom";

test("Testing buttons and triggered requests", async () => {

    jest.spyOn(axios, 'put').mockImplementation((url: string, data: any) => {
        expect(url).toEqual('http://localhost:8080/api/kanban/next');
        expect(data).toEqual({id: "9000", task: "Merge-Request abschicken", description: "An Andre", status: "OPEN"});
        return Promise.resolve({});
    })

    const onTaskCreation = jest.fn(() => {});

    render(
                <GalleryItem todo={{
                id: "9000",
                status: "OPEN",
                task: "Merge-Request abschicken",
                description: "An Andre"
            }} onTaskChange={onTaskCreation} />, {wrapper: MemoryRouter}
        )

    const nextButton = screen.getByTestId("next-button");
    const editButton = screen.getByTestId("edit-button");

    nextButton.click();
    editButton.click();

    await (() => {
        expect(onTaskCreation).toHaveBeenCalledTimes(2);
    })
})