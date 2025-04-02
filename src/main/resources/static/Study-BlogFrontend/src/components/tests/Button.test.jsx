import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import Button from '../Button';
import userEvent from '@testing-library/user-event';
import { vi } from 'vitest';

describe('Button', () => {
    test("Button should exist in the document with the default text content", () => {
        render(<Button />);
        const button = screen.getByRole("button");
        expect(button).toBeInTheDocument();

    });

    test('Button should render with custom text', () => {
        render(<button><p>hi</p></button>);
        const button = screen.getByRole("button");
        expect(button).toHaveTextContent("hi");
    });

    test('onClick function should be called once', async () => {
        const handleOnClick = vi.fn();
        render(<Button onClick={handleOnClick} />);
        const button = screen.getByRole("button");
        await userEvent.click(button);  // ✅ Használjunk userEvent-et
        expect(handleOnClick).toHaveBeenCalledTimes(1);
    });

    test("renders the button with the correct className", () => {
        render(<Button className="button" />);
        const button = screen.getByRole("button");
        expect(button).toHaveClass("button");
    });

    test("Click function should not work when the button is disabled", async () => {
        const handleClick = vi.fn();
        render(<Button disabled={true} onClick={handleClick}><h1>Click Me</h1></Button>);
        const button = screen.getByRole("button");
        await userEvent.click(button);
        expect(handleClick).not.toHaveBeenCalled();  // ✅ Fix
        expect(button).toBeDisabled();
    });


});
